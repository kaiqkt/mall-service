package me.kaique.application.web.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import io.azam.ulidj.ULID
import me.kaique.domain.entities.*
import java.time.LocalDate
import java.time.LocalDateTime

data class IndividualRegisterRequest(
    val fullName: String,
    val cpf: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer::class)
    val birthDay: LocalDate,
    val store: StoreRequest
)

fun IndividualRegisterRequest.toDomain(accountId: String): StoreInformation {
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
        fullName = this.fullName,
        cpf = this.cpf,
        birthDay = this.birthDay,
        address = this.store.address.toDomain(),
        fiscalType = FiscalType.LEGAL,
        createdAt = LocalDateTime.now()
    )

    return StoreInformation(store, fiscal)
}
