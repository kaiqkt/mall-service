package me.kaique.application.web.dto

import me.kaique.domain.entities.StoreCategory

data class StoreRequest(
    val storeName: String,
    val storeBio: String,
    val address: AddressRequest,
    val storeCategory: StoreCategory
)
