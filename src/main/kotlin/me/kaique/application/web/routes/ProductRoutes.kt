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
                    ctx.getContentTypeWithoutCharset() == Constants.REGISTER_PRODUCT -> {
                        productController.registerProduct(ctx)
                    }
                    else -> ctx.notAcceptable()
                }
            }, setOf(Roles.CUSTOMER))

            get("/:storeId/:productId", { ctx -> productController.getProduct(ctx) }, setOf(Roles.ANYONE))

            get("/category/:storeId/:categoryName", { ctx -> productController.getProductsByCategory(ctx) }, setOf(Roles.ANYONE))

            get("/:storeId", { ctx -> productController.getProducts(ctx) }, setOf(Roles.ANYONE))

            post("/category/:categoryName", { ctx ->
                when {
                    ctx.getContentTypeWithoutCharset() == Constants.REGISTER_PRODUCT_CATEGORY -> {
                        productController.registerProductCategory(ctx)
                    }
                    else -> ctx.notAcceptable()
                }
            }, setOf(Roles.CUSTOMER))
        }
    }
}