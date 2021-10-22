package me.kaique.application.web.routes

import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post
import me.kaique.application.configs.Constants
import me.kaique.application.configs.Roles
import me.kaique.application.configs.getContentTypeWithoutCharset
import me.kaique.application.configs.notAcceptable
import me.kaique.application.web.controller.StoreController

class StoreRoutes(private val storeController: StoreController) {
    fun register() {
        path("/legal") {
            post({ ctx ->
                when {
                    ctx.getContentTypeWithoutCharset() == Constants.REGISTER_LEGAL -> {
                        storeController.registerLegalStore(ctx)
                    }
                    else -> ctx.notAcceptable()
                }
            }, setOf(Roles.CUSTOMER))
        }
        path("/individual") {
            post({ ctx ->
                when {
                    ctx.getContentTypeWithoutCharset() == Constants.REGISTER_INDIVIDUAL -> {
                        storeController.registerLegalStore(ctx)
                    }
                    else -> ctx.notAcceptable()
                }
            }, setOf(Roles.CUSTOMER))
        }
    }
}