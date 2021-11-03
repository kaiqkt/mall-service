package me.kaique.application.configs.modules

import me.kaique.application.web.controller.ProductController
import me.kaique.application.web.routes.ProductRoutes
import me.kaique.domain.services.ProductService
import org.koin.dsl.module.module

val productModules = module {
    single {
        ProductService(
            storeRepository = get()
        )
    }

    single {
        ProductController(
            productService = get()
        )
    }

    single {
        ProductRoutes(
            productController = get()
        )
    }
}