package me.kaique.application.configs.modules

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import me.kaique.application.web.controller.StoreCategoryController
import me.kaique.application.web.controller.StoreController
import me.kaique.application.web.routes.StoreCategoryRoutes
import me.kaique.application.web.routes.StoreRoutes
import me.kaique.domain.gateways.persistence.StoreRepository
import me.kaique.domain.gateways.singleregistry.SingleRegistryService
import me.kaique.domain.services.StoreCategoryService
import me.kaique.domain.services.StoreService
import me.kaique.domain.services.ValidationService
import me.kaique.resources.repositories.StoreMongoRepository
import me.kaique.resources.singleregistry.clients.SingleRegistryClient
import me.kaique.resources.singleregistry.gateways.SingleRegistryServiceImpl
import org.koin.dsl.context.ModuleDefinition
import org.koin.dsl.module.module

val storeModules = module {
    single<StoreRepository> {
        StoreMongoRepository(
            mongo = get(),
            collectionName = getProperty("MONGODB_STORE_COLLECTION")
        )
    }

    single {
        SingleRegistryClient(
            serviceUrl = getProperty("SINGLE_REGISTRY_SERVICE_URL"),
            serviceSecret = getProperty("SINGLE_REGISTRY_SERVICE_SECRET")
        )
    }

    single<SingleRegistryService> {
        SingleRegistryServiceImpl(
            singleRegistryClient = get()
        )
    }

    single {
        StoreCategoryService(
            categories = getJsonProperty("STORE_CATEGORIES")
        )
    }

    single {
        ValidationService(
            storeRepository = get(),
            storeCategoryService = get(),
            fiscalInformationRepository = get()
        )
    }

    single {
        StoreService(
            validationService = get(),
            singleRegistryService = get(),
            storeRepository = get(),
            fiscalInformationRepository = get()
        )
    }

    single {
        StoreController(
            storeService = get()
        )
    }

    single {
        StoreCategoryController(
            storeCategoryService = get()
        )
    }

    single {
        StoreRoutes(
            storeController = get()
        )
    }

    single {
        StoreCategoryRoutes(
            storeCategoryController = get()
        )
    }
}

private inline fun <reified T> ModuleDefinition.getJsonProperty(key: String): T{
    val value: String = getProperty(key)
    return jacksonObjectMapper().readValue(value)
}