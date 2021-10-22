package me.kaique.application.web.dto

import me.kaique.domain.entities.ProductCatalog

data class ProductCatalogResponse(
    val id: String,
    val products: List<ProductResponse>
)

fun ProductCatalog.toResponse(): ProductCatalogResponse = ProductCatalogResponse(
    id = this.id,
    products = products.map { it.toResponse() }
)
