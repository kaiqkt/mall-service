package me.kaique.domain.entities

import java.time.LocalDate
import java.time.LocalDateTime

data class FiscalInformation(
    val id: String,
    val accountId: String,
    var cnpj: String? = null,
    var cpf: String? = null,
    val businessName: String? = null,
    val fullName: String? = null,
    val birthDay: LocalDate? = null,
    val address: Address,
    val fiscalType: FiscalType,
    val createdAt: LocalDateTime
){
    init {
        this.cpf = cpf?.replace("-", "", false)
        this.cpf = cpf?.replace(".", "", false)
        this.cpf = cpf?.replace("/", "", false)
        this.cnpj = cnpj?.replace("-", "", false)
        this.cnpj = cnpj?.replace(".", "", false)
        this.cnpj = cnpj?.replace("/", "", false)
    }
}


