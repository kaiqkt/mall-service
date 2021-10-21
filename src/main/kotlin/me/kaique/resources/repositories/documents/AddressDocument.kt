package me.kaique.resources.repositories.documents

import me.kaique.domain.entities.Address
import me.kaique.domain.entities.Country

data class AddressDocument(
    val addressId: String,
    var zipCode: String,
    val street: String,
    val number: String,
    var complement: String? = null,
    val city: String,
    val state: String,
    val country: Country
){
    fun toAddress(): Address = Address(
        addressId = this.addressId,
        zipCode = this.zipCode,
        street = this.street,
        number = this.number,
        complement = this.complement,
        city = this.city,
        state = this.state,
        country = this.country
    )

    companion object {
        fun toDocument(address: Address): AddressDocument = AddressDocument(
        addressId = address.addressId,
        zipCode = address.zipCode,
        street = address.street,
        number = address.number,
        complement = address.complement,
        city = address.city,
        state = address.state,
        country = address.country
        )
    }
}
