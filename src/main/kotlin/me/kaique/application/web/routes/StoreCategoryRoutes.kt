package me.kaique.application.web.routes

import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import me.kaique.application.configs.Roles
import me.kaique.application.web.controller.StoreCategoryController

class StoreCategoryRoutes(private val storeCategoryController: StoreCategoryController) {
    fun register() {
        path("/category") {
            get({ ctx -> storeCategoryController.getCategories(ctx) }, setOf(Roles.ANYONE))
        }
    }
}