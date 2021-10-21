package me.kaique.application.configs.modules

import me.kaique.domain.gateways.persistence.FiscalInformationRepository
import me.kaique.resources.repositories.FiscalInformationMongoRepository
import org.koin.dsl.module.module

val fiscalInformationModules = module {
    single<FiscalInformationRepository> {
        FiscalInformationMongoRepository(
            mongo = get(),
            collectionName = getProperty("MONGODB_FISCAL_INFORMATION_COLLECTION")
        )
    }
}