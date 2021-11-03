package me.kaique.resources.repositories.documents

import me.kaique.domain.entities.Product
import java.math.BigDecimal

data class ProductDocument(
    val _id: String,
    val productName: String,
    val description: String,
    val price: BigDecimal,
    val options: List<String>? = null,
    val productCategory: String
){
    fun toProduct(): Product = Product(
        id = this._id,
        productName = this.productName,
        description = this.description,
        price = this.price,
        options = this.options,
        productCategory = this.productCategory
    )

    companion object {
        fun toDocument(product: Product): ProductDocument = ProductDocument(
            _id = product.id,
            productName = product.productName,
            description = product.description,
            price = product.price,
            options = product.options,
            productCategory = product.productCategory
        )
    }
}