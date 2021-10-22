package me.kaique.domain.services

import me.kaique.domain.entities.Product
import me.kaique.domain.entities.ProductCatalog
import me.kaique.domain.exceptions.StoreNotFoundException
import me.kaique.domain.gateways.persistence.ProductRepository
import me.kaique.domain.gateways.persistence.StoreRepository

class ProductService(private val storeRepository: StoreRepository, private val productRepository: ProductRepository) {

    fun create(product: Product, accountId: String): ProductCatalog {
        val store = storeRepository.findByAccountId(accountId)
            ?: throw StoreNotFoundException("Store with account id $accountId not found")

        //procurar pelas categorias

        return productRepository.save(product, store.id)
    }
}