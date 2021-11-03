package me.kaique.domain.gateways.persistence

import me.kaique.domain.entities.Store
import me.kaique.domain.entities.Account
import me.kaique.domain.entities.Product

interface StoreRepository {
    fun save(store: Store, account: Account)
    fun save(product: Product, accountId: String)
    fun save(accountId: String, productCategory: String)
    fun findByStoreName(storeName: String): Store?
    fun findStoresByCategory(category: String): List<Store>
    fun findByAccountId(accountId: String): Store?
    fun findStores(): List<Store>
    fun findProduct(storeId: String, productId: String): Product?
    fun findProductsByCategoryName(storeId: String, categoryName: String): List<Product>?
    fun findProducts(storeId: String): List<Product>?
}