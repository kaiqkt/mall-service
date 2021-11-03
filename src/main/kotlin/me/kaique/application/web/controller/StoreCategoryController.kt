package me.kaique.application.web.controller

import io.javalin.http.Context
import me.kaique.domain.services.StoreCategoryService
import org.eclipse.jetty.http.HttpStatus

class StoreCategoryController(private val storeCategoryService: StoreCategoryService) {

    fun getCategories(ctx: Context) {
        storeCategoryService.getAllCategories().apply {
            ctx.json(this).status(HttpStatus.OK_200)
        }
    }
}