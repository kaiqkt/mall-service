package me.kaique.resources.repositories

import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.FindOneAndUpdateOptions
import com.mongodb.client.model.ReturnDocument
import me.kaique.domain.entities.Product
import me.kaique.domain.entities.ProductCatalog
import me.kaique.domain.gateways.persistence.ProductRepository
import me.kaique.resources.repositories.documents.ProductCatalogDocument
import me.kaique.resources.repositories.documents.ProductDocument
import me.kaique.resources.repositories.exceptions.UnexpectedPersistenceException
import org.litote.kmongo.eq
import org.litote.kmongo.push
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ProductMongoRepository(
    mongo: MongoDatabase,
    collectionName: String
) : ProductRepository {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val collection: MongoCollection<ProductCatalogDocument> = mongo.getCollection(
        collectionName,
        ProductCatalogDocument::class.java
    )

    override fun save(product: Product, storeId: String): ProductCatalog {
        return collection.findOneAndUpdate(
            ProductCatalogDocument::_id eq storeId,
            push(ProductCatalogDocument::products, ProductDocument.toDocument(product)),
            FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
        )?.toProductCatalog().also {
            log.info(
                "Inserted new product with id ${product.id}" +
                        " on product catalog with id $storeId and persisted successfully"
            )
        } ?: throw UnexpectedPersistenceException("Unable to persist product: ${product.id}")
    }

    override fun saveNewProductCatalog(storeId: String) {
        val document = ProductCatalogDocument(_id = storeId, products = listOf())

        try {
            collection.insertOne(document)
            log.info("New product catalog with id ${document._id} persisted successfully")
        } catch (e: MongoException) {
            throw UnexpectedPersistenceException("Unable to insert product catalog ${document._id} on database")
        }
    }

    override fun findProductCatalog(storeId: String): ProductCatalog? {
        TODO("Not yet implemented")
    }
}