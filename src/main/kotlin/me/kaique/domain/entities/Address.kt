package me.kaique.domain.entities

data class Address (
    val addressId: String,
    var zipCode: String,
    val street: String,
    val number: String,
    var complement: String? = null,
    val city: String,
    val state: String,
    val country: Country
) {
    init {
        this.zipCode = zipCode.replace("-", "", false)
    }
}