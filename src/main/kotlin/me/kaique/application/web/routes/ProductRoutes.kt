package me.kaique.application.web.routes

import io.javalin.apibuilder.ApiBuilder.*
import me.kaique.application.configs.Constants
import me.kaique.application.configs.Roles
import me.kaique.application.configs.getContentTypeWithoutCharset
import me.kaique.application.configs.notAcceptable
import me.kaique.application.web.controller.ProductController

class ProductRoutes(private val productController: ProductController) {
    fun register() {
        path("/product") {
            post({ ctx ->
                when {
                    ctx.getContentTypeWithoutCharset() == Constants.REGISTER_LEGAL -> {
                        productController.registerProduct(ctx)
                    }
                    else -> ctx.notAcceptable()
                }
            }, setOf(Roles.CUSTOMER))
        }
    }
}