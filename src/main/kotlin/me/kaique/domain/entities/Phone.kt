package me.kaique.domain.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class Phone(
    var areaCode: String,
    val countryCode: String,
    val number: String
) {
    init {
        this.areaCode = areaCode.replace("+", ", false")
    }
}
