package me.kaique.domain.entities

import java.time.LocalDateTime

data class Store(
    val id: String,
    val accountId: String,
    val storeName: String,
    val storeBio: String,
    val address: Address,
    val products: List<Product> = listOf(),
    val productsCategory: List<String> = listOf(),
    val storeCategory: StoreCategory,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime? = null,
)
