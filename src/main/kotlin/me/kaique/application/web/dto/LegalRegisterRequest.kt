package me.kaique.application.web.dto

import io.azam.ulidj.ULID
import me.kaique.domain.entities.*
import java.time.LocalDateTime

data class LegalRegisterRequest(
    val businessName: String,
    val cnpj: String,
    val store: StoreRequest
)

fun LegalRegisterRequest.toDomain(accountId: String): StoreInformation {
    val store = Store(
        id = ULID.random(),
        accountId = accountId,
        storeName = this.store.storeName,
        storeBio = this.store.storeBio,
        address = this.store.address.toDomain(),
        storeCategory = this.store.storeCategory,
        createdAt = LocalDateTime.now()
    )

    val fiscal = FiscalInformation(
        id = store.id,
        accountId = accountId,
        businessName = this.businessName,
        cnpj = this.cnpj,
        address = this.store.address.toDomain(),
        fiscalType = FiscalType.LEGAL,
        createdAt = LocalDateTime.now()
    )

    return StoreInformation(store, fiscal)
}