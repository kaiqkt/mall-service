package me.kaique.resources.singleregistry.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import me.kaique.domain.entities.Phone
import java.time.LocalDate

data class AccountResponse(
    val accountId: String,
    val userName: String,
    val fullName: String,
    val email: String,
    val gender: Gender,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val birthday: LocalDate,
    val addresses: List<AddressResponse>,
    val phone: Phone
)
