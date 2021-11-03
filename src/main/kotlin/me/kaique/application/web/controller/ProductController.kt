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

    fun registerProductCategory(ctx: Context) {
        val categoryName = ctx.pathParam<String>("categoryName").get()
        val accountId = ctx.getAccountIdByToken()

        productService.createProductCategory(accountId, categoryName)
        ctx.status(HttpStatus.NO_CONTENT_204)

    }

    fun getProduct(ctx: Context) {
        val storeId = ctx.pathParam<String>("storeId").get()
        val productId = ctx.pathParam<String>("productId").get()

        productService.getProduct(storeId, productId)?.apply {
            ctx.json(this.toResponse()).status(HttpStatus.OK_200)
        }
    }

    fun getProductsByCategory(ctx: Context) {
        val storeId = ctx.pathParam<String>("storeId").get()
        val categoryName = ctx.pathParam<String>("categoryName").get()

        productService.getProductsByCategoryName(storeId, categoryName)?.map { it.toResponse()}?.apply {
                ctx.json(this).status(HttpStatus.OK_200)
            }
        }

    fun getProducts(ctx: Context) {
        val storeId = ctx.pathParam<String>("storeId").get()

        productService.getProducts(storeId)?.map { it.toResponse()}?.apply {
            ctx.json(this).status(HttpStatus.OK_200)
        }
    }
}