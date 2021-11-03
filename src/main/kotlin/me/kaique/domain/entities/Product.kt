package me.kaique.domain.entities

import java.math.BigDecimal

data class Product(
    val id: String,
    val productName: String,
    val description: String,
    val price: BigDecimal,
    val options: List<String>? = null,
    val productCategory: String
)
