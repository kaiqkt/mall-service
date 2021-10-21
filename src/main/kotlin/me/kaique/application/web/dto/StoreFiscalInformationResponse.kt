package me.kaique.application.web.dto

import me.kaique.domain.entities.StoreInformation

data class StoreFiscalInformationResponse(
    val store: StoreResponse,
    val fiscalInformation: FiscalInformationResponse
)

fun StoreInformation.toResponse(): StoreFiscalInformationResponse =
    StoreFiscalInformationResponse(
        store = this.store.toResponse(),
        fiscalInformation = this.fiscalInformation.toResponse()
    )
