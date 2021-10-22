package me.kaique.resources.repositories.documents

import me.kaique.domain.entities.ProductCatalog

data class ProductCatalogDocument(
    val _id: String,
    val products: List<ProductDocument>
) {
    fun toProductCatalog(): ProductCatalog = ProductCatalog(
        id = this._id,
        products = this.products.map { it.toProduct() }
    )

    companion object {
        fun toDocument(productCatalog: ProductCatalog): ProductCatalogDocument = ProductCatalogDocument(
            _id = productCatalog.id,
            products = productCatalog.products.map { ProductDocument.toDocument(it) }
        )
    }
}