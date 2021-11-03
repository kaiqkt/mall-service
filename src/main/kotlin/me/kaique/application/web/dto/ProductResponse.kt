package me.kaique.application.web.dto

import me.kaique.domain.entities.Product
import java.math.BigDecimal

data class ProductResponse(
    val id: String,
    val productName: String,
    val description: String,
    val price: BigDecimal,
    val options: List<String>? = null,
    val productCategory: String
)

fun Product.toResponse() = ProductResponse(
    id= this.id,
    productName = this.productName,
    description = this.description,
    price = this.price,
    options = this.options,
    productCategory = this.productCategory
)