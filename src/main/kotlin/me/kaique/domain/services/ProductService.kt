package me.kaique.domain.services

import me.kaique.domain.entities.Product
import me.kaique.domain.exceptions.InvalidProductCategoryException
import me.kaique.domain.exceptions.StoreNotFoundException
import me.kaique.domain.gateways.persistence.StoreRepository

class ProductService(private val storeRepository: StoreRepository) {

    fun create(product: Product, accountId: String): Product {
        val store = storeRepository.findByAccountId(accountId)
            ?: throw StoreNotFoundException("Store with account id $accountId not found")

        if (!store.productsCategory.contains(product.productCategory)) {
            throw InvalidProductCategoryException("Product category ${product.productCategory} not exist")
        }

        storeRepository.save(product, accountId)

        return product
    }

    fun createProductCategory(accountId: String, categoryName: String) {
        storeRepository.save(accountId, categoryName)
    }

    fun getProduct(storeId: String, productId: String): Product? {
        return storeRepository.findProduct(storeId, productId)
    }

    fun getProductsByCategoryName(storeId: String, categoryName: String): List<Product>? {
        return storeRepository.findProductsByCategoryName(storeId, categoryName)
    }

    fun getProducts(storeId: String): List<Product>? {
        return storeRepository.findProducts(storeId)
    }
}