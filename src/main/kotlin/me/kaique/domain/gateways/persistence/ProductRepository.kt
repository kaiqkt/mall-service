package me.kaique.domain.gateways.persistence

import me.kaique.domain.entities.Product
import me.kaique.domain.entities.ProductCatalog

interface ProductRepository {
    fun save(product: Product, storeId: String): ProductCatalog
    fun saveNewProductCatalog(storeId: String)
    fun findProductCatalog(storeId: String): ProductCatalog?
}