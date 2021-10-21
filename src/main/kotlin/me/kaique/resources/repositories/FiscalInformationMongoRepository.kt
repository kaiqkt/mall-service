package me.kaique.resources.repositories

import com.mongodb.MongoException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import me.kaique.domain.entities.FiscalInformation
import me.kaique.domain.gateways.persistence.FiscalInformationRepository
import me.kaique.resources.repositories.exceptions.UnexpectedPersistenceException
import me.kaique.resources.repositories.documents.FiscalInformationDocument
import me.kaique.domain.entities.Account
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class FiscalInformationMongoRepository(
    mongo: MongoDatabase,
    collectionName: String
): FiscalInformationRepository {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val collection: MongoCollection<FiscalInformationDocument> = mongo.getCollection(
        collectionName,
        FiscalInformationDocument::class.java
    )

    override fun save(fiscalInformation: FiscalInformation, account: Account) {
        val document = FiscalInformationDocument.toDocument(fiscalInformation, account)

        try {
            collection.insertOne(document)
            log.info("Fiscal Information with id ${fiscalInformation.id} persisted successfully")
        } catch (e: MongoException) {
            throw UnexpectedPersistenceException("Unable to insert fiscal information $fiscalInformation on database")
        }
    }

    override fun findByAccountId(accountId: String): FiscalInformation? {
        val document = collection.findOne(FiscalInformationDocument::accountId eq accountId)

        return document?.toFiscalInformation()
    }

    override fun findByCnpj(cnpj: String): FiscalInformation? {
        val document = collection.findOne(FiscalInformationDocument::cnpj eq cnpj)

        return document?.toFiscalInformation()
    }

    override fun findByCpf(cpf: String): FiscalInformation? {
        val document = collection.findOne(FiscalInformationDocument::cpf eq cpf)

        return document?.toFiscalInformation()
    }
}