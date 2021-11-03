package me.kaique.application.web.dto

import io.azam.ulidj.ULID
import me.kaique.domain.entities.Product
import java.math.BigDecimal

data class ProductRequest(
    val productName: String,
    val description: String,
    val price: BigDecimal,
    val options: List<String>? = null,
    val productCategory: String
)

fun ProductRequest.toDomain() = Product(
    id= ULID.random(),
    productName = this.productName,
    description = this.description,
    price = this.price,
    options = this.options,
    productCategory = this.productCategory
)