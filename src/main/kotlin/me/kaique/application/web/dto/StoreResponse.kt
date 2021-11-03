package me.kaique.application.web.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import me.kaique.domain.entities.Address
import me.kaique.domain.entities.Product
import me.kaique.domain.entities.Store
import me.kaique.domain.entities.StoreCategory
import java.time.LocalDateTime

data class StoreResponse(
    val id: String,
    val accountId: String,
    val storeName: String,
    val storeBio: String,
    val address: Address,
    val storeCategory: StoreCategory,
    val products: List<ProductResponse>,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    val updateAt: LocalDateTime? = null,
)

fun Store.toResponse() = StoreResponse(
    id = this.id,
    accountId = this.accountId,
    storeName = this.storeName,
    storeBio = this.storeBio,
    address = this.address,
    storeCategory = this.storeCategory,
    products = this.products.map { it.toResponse() },
    createdAt = LocalDateTime.now(),
    updateAt = this.updateAt
)