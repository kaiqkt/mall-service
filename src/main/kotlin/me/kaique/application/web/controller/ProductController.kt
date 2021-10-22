package me.kaique.application.web.controller

import io.javalin.http.Context
import me.kaique.application.configs.getAccountIdByToken
import me.kaique.application.web.dto.ProductRequest
import me.kaique.application.web.dto.toDomain
import me.kaique.application.web.dto.toResponse
import me.kaique.domain.services.ProductService
import org.eclipse.jetty.http.HttpStatus

class ProductController(private val productService: ProductService) {

    fun registerProduct(ctx: Context) {
        val body = ctx.body<ProductRequest>()
        val accountId = ctx.getAccountIdByToken()
        productService.create(body.toDomain(), accountId).apply {
            ctx.json(this.toResponse()).status(HttpStatus.OK_200)
        }
    }
}