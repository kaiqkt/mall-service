package me.kaique.resources.singleregistry.entities

import me.kaique.domain.entities.Country

data class AddressResponse (
    val addressId: String,
    val addressType: AddressType,
    var zipCode: String,
    val street: String,
    val number: String,
    var complement: String? = null,
    val city: String,
    val state: String,
    val country: Country
)