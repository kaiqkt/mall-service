package me.kaique.application.web.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import me.kaique.domain.entities.Address
import me.kaique.domain.entities.FiscalInformation
import me.kaique.domain.entities.FiscalType
import java.time.LocalDate
import java.time.LocalDateTime

data class FiscalInformationResponse(
    val id: String,
    val accountId: String,
    val cnpj: String? = null,
    val cpf: String? = null,
    val businessName: String? = null,
    val fullName: String? = null,
    val birthDay: LocalDate? = null,
    val address: Address,
    val fiscalType: FiscalType,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime
)

fun FiscalInformation.toResponse(): FiscalInformationResponse =
    FiscalInformationResponse(
        id = this.id,
        accountId = accountId,
        cnpj = this.cnpj,
        cpf = this.cpf,
        businessName = this.businessName,
        fullName = this.businessName,
        birthDay = this.birthDay,
        address = this.address,
        fiscalType = this.fiscalType,
        createdAt = this.createdAt
    )
