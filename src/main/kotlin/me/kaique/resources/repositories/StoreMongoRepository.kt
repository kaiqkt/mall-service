package me.kaique.resources.repositories

import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import me.kaique.domain.entities.Account
import me.kaique.domain.entities.Product
import me.kaique.domain.entities.Store
import me.kaique.domain.entities.StoreCategory
import me.kaique.domain.gateways.persistence.StoreRepository
import me.kaique.resources.repositories.documents.ProductDocument
import me.kaique.resources.repositories.documents.StoreDocument
import me.kaique.resources.repositories.exceptions.UnexpectedPersistenceException
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.push
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class StoreMongoRepository(
    mongo: MongoDatabase,
    collectionName: String
) : StoreRepository {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val collection: MongoCollection<StoreDocument> = mongo.getCollection(
        collectionName,
        StoreDocument::class.java
    )

    override fun save(store: Store, account: Account) {
        val document = StoreDocument.toDocument(store, account)

        try {
            collection.insertOne(document)
            log.info("Store with id ${store.id} persisted successfully")
        } catch (e: MongoException) {
            throw UnexpectedPersistenceException("Unable to insert store $store on database")
        }
    }

    override fun save(product: Product, accountId: String) {
        try {
            collection.findOneAndUpdate(
                StoreDocument::accountId eq accountId,
                push(StoreDocument::products, ProductDocument.toDocument(product))
            ).also {
                log.info(
                    "Inserted new product with id ${product.id} and persisted successfully"
                )
            }
        } catch (e: MongoException) {
            throw UnexpectedPersistenceException("Unable to insert product ${product.id} on database")
        }
    }

    override fun save(accountId: String, productCategory: String) {
        try {
            collection.findOneAndUpdate(
                StoreDocument::accountId eq accountId,
                push(StoreDocument::productsCategory, productCategory)
            ).also {
                log.info(
                    "Inserted new product category  and persisted successfully"
                )
            }
        } catch (e: MongoException) {
            throw UnexpectedPersistenceException("Unable to insert new product category on database")
        }
    }

    override fun findByAccountId(accountId: String): Store? {
        val document = collection.findOne(StoreDocument::accountId eq accountId)

        return document?.toStore()
    }

    override fun findStores(): List<Store> {
        val document = collection.find().toList()

        return document.map { it.toStore() }
    }

    override fun findProduct(storeId: String, productId: String): Product? {
        val document = collection.findOne(StoreDocument::_id eq storeId)
        val product = document?.products?.first { it._id == productId }
        return product?.toProduct()
    }

    override fun findProductsByCategoryName(storeId: String, categoryName: String): List<Product>? {
        val document = collection.findOne(StoreDocument::_id eq storeId)
        val product = document?.products?.filter { it.productCategory == categoryName }
        return product?.map { it.toProduct() }
    }

    override fun findProducts(storeId: String): List<Product>? {
        val document = collection.findOne(StoreDocument::_id eq storeId)

        return document?.products?.map { it.toProduct() }
    }

    override fun findByStoreName(storeName: String): Store? {
        val document = collection.findOne(StoreDocument::storeName eq storeName)

        return document?.toStore()
    }

    override fun findStoresByCategory(category: String): List<Store> {
        val document = collection.find(StoreDocument::storeCategory / StoreCategory::categoryName eq category).toList()

        return document.map { it.toStore() }
    }
}