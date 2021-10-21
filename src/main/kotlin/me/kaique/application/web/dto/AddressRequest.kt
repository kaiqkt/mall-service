package me.kaique.application.web.dto

import io.azam.ulidj.ULID
import me.kaique.domain.entities.Address
import me.kaique.domain.entities.Country

data class AddressRequest(
    var zipCode: String,
    val street: String,
    val number: String,
    var complement: String? = null,
    val city: String,
    val state: String,
    val country: Country
)

fun AddressRequest.toDomain() = Address(
    addressId = ULID.random(),
    zipCode = this.zipCode,
    street = this.street,
    number = this.number,
    complement = this.complement,
    city = this.city,
    state = this.state,
    country = this.country
)
