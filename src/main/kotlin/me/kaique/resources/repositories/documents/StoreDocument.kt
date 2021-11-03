package me.kaique.resources.repositories.documents

import me.kaique.domain.entities.Account
import me.kaique.domain.entities.Phone
import me.kaique.domain.entities.Store
import me.kaique.domain.entities.StoreCategory
import java.time.LocalDateTime

data class StoreDocument(
    val _id: String,
    val accountId: String,
    val storeName: String,
    val storeBio: String,
    val address: AddressDocument,
    val email: String,
    val phone: Phone?,
    val storeCategory: StoreCategory,
    val productsCategory: List<String>,
    val products: List<ProductDocument>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
) {
    fun toStore(): Store =
        Store(
            id = this._id,
            accountId = this.accountId,
            storeName = this.storeName,
            storeBio = this.storeBio,
            address = this.address.toAddress(),
            storeCategory = this.storeCategory,
            productsCategory = this.productsCategory,
            products = this.products.map { it.toProduct() },
            createdAt = this.createdAt,
            updateAt = this.updatedAt
        )

    companion object {
        fun toDocument(store: Store, account: Account): StoreDocument =
            StoreDocument(
                _id = store.id,
                accountId = store.accountId,
                storeName = store.storeName,
                storeBio = store.storeBio,
                address = AddressDocument.toDocument(store.address),
                email = account.email,
                phone = account.phone,
                storeCategory = store.storeCategory,
                productsCategory = store.productsCategory,
                products = store.products.map { ProductDocument.toDocument(it) },
                createdAt = store.createdAt,
                updatedAt = store.updateAt
            )
    }
}

