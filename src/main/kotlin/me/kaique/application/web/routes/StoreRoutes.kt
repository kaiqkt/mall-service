package me.kaique.application.web.routes

import io.javalin.apibuilder.ApiBuilder.*
import me.kaique.application.configs.Constants
import me.kaique.application.configs.Roles
import me.kaique.application.configs.getContentTypeWithoutCharset
import me.kaique.application.configs.notAcceptable
import me.kaique.application.web.controller.StoreController

class StoreRoutes(private val storeController: StoreController) {
    fun register() {
        path("/store") {
            post("/legal",{ ctx ->
                when {
                    ctx.getContentTypeWithoutCharset() == Constants.REGISTER_LEGAL -> {
                        storeController.registerLegalStore(ctx)
                    }
                    else -> ctx.notAcceptable()
                }
            }, setOf(Roles.CUSTOMER))

            post("/individual", { ctx ->
                when {
                    ctx.getContentTypeWithoutCharset() == Constants.REGISTER_INDIVIDUAL -> {
                        storeController.registerIndividualStore(ctx)
                    }
                    else -> ctx.notAcceptable()
                }
            }, setOf(Roles.CUSTOMER))

            get("/info", { ctx -> storeController.getStore(ctx) } , setOf(Roles.CUSTOMER))

            get({ ctx -> storeController.getAllStores(ctx) } , setOf(Roles.ANYONE))

            get("/name/:storeName", { ctx -> storeController.getStoreByName(ctx) } , setOf(Roles.ANYONE))

            get("/category/:category", { ctx -> storeController.getStoresByCategory(ctx) } , setOf(Roles.ANYONE))
        }
    }
}