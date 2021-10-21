package me.kaique.application.web.dto

import io.azam.ulidj.ULID
import me.kaique.domain.entities.*
import java.time.LocalDateTime

data class LegalStoreRegisterRequest(
    val businessName: String,
    val cnpj: String,
    val storeName: String,
    val storeBio: String,
    val address: AddressRequest,
    val storeCategory: StoreCategory
)

fun LegalStoreRegisterRequest.toDomain(accountId: String): StoreInformation {
    val store = Store(
        id = ULID.random(),
        accountId = accountId,
        storeName = this.storeName,
        storeBio = this.storeBio,
        address = this.address.toDomain(),
        storeCategory = this.storeCategory,
        createdAt = LocalDateTime.now()
    )

    val fiscal = FiscalInformation(
        id = store.id,
        accountId = accountId,
        businessName = this.businessName,
        cnpj = this.cnpj,
        address = this.address.toDomain(),
        fiscalType = FiscalType.LEGAL,
        createdAt = LocalDateTime.now()
    )

    return StoreInformation(store, fiscal)
}