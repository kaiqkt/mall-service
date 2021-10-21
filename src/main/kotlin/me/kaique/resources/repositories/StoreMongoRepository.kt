package me.kaique.resources.repositories

import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import me.kaique.domain.entities.Store
import me.kaique.domain.gateways.persistence.StoreRepository
import me.kaique.resources.repositories.exceptions.UnexpectedPersistenceException
import me.kaique.resources.repositories.documents.StoreDocument
import me.kaique.domain.entities.Account
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class StoreMongoRepository(
    mongo: MongoDatabase,
    collectionName: String
): StoreRepository {

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

    override fun findByAccountId(accountId: String): Store? {
        val document = collection.findOne(StoreDocument::accountId eq accountId)

        return document?.toStore()
    }

    override fun findByStoreName(storeName: String): Store? {
        val document = collection.findOne(StoreDocument::storeName eq storeName)

        return document?.toStore()
    }
}