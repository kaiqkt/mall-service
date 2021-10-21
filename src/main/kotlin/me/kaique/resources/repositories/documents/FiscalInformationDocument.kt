package me.kaique.resources.repositories.documents

import me.kaique.domain.entities.FiscalInformation
import me.kaique.domain.entities.FiscalType
import me.kaique.domain.entities.Phone
import me.kaique.domain.entities.Account
import java.time.LocalDate
import java.time.LocalDateTime

data class FiscalInformationDocument(
    val _id: String,
    val accountId: String,
    val cnpj: String? = null,
    val cpf: String? = null,
    val businessName: String? = null,
    val fullName: String? = null,
    val birthDay: LocalDate? = null,
    val address: AddressDocument,
    val email: String,
    val phone: Phone,
    val fiscalType: FiscalType,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
) {
    fun toFiscalInformation(): FiscalInformation =
        FiscalInformation(
            id = this._id,
            accountId = accountId,
            cnpj = this.cnpj,
            cpf = this.cpf,
            businessName = this.businessName,
            fullName = this.businessName,
            birthDay = this.birthDay,
            address = this.address.toAddress(),
            fiscalType = this.fiscalType,
            createdAt = this.createdAt
        )

    companion object {
        fun toDocument(fiscalInformation: FiscalInformation, account: Account): FiscalInformationDocument =
            FiscalInformationDocument(
                _id = fiscalInformation.id,
                accountId = fiscalInformation.accountId,
                cnpj = fiscalInformation.cnpj,
                cpf = fiscalInformation.cpf,
                businessName = fiscalInformation.businessName,
                fullName = fiscalInformation.businessName,
                birthDay = fiscalInformation.birthDay,
                address = AddressDocument.toDocument(fiscalInformation.address),
                email = account.email,
                phone = account.phone,
                fiscalType = fiscalInformation.fiscalType,
                createdAt = fiscalInformation.createdAt
            )
    }
}

