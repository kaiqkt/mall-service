package me.kaique.domain.services

import me.kaique.domain.entities.FiscalType
import me.kaique.domain.entities.Store
import me.kaique.domain.entities.StoreInformation
import me.kaique.domain.exceptions.StoreNotFoundException
import me.kaique.domain.gateways.persistence.FiscalInformationRepository
import me.kaique.domain.gateways.persistence.StoreRepository
import me.kaique.domain.gateways.singleregistry.SingleRegistryService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class StoreService(
    private val validationService: ValidationService,
    private val singleRegistryService: SingleRegistryService,
    private val storeRepository: StoreRepository,
    private val fiscalInformationRepository: FiscalInformationRepository
) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun create(storeInformation: StoreInformation): StoreInformation {
        val store = storeInformation.store
        val fiscalInformation = storeInformation.fiscalInformation

        if (fiscalInformation.fiscalType == FiscalType.LEGAL) {
            validationService.validateLegal(
                fiscalInformation.cnpj!!,
                store.storeName,
                store.storeCategory,
                store.accountId
            )
        } else {
            validationService.validateIndividual(
                fiscalInformation.cpf!!,
                store.storeName,
                store.storeCategory,
                store.accountId
            )
        }

        val account = singleRegistryService.findAccountById(store.accountId)

        storeRepository.save(store, account)
        fiscalInformationRepository.save(fiscalInformation, account)

        log.info("Legal store with id ${store.id} created successfully")

        return storeInformation
    }

    fun getStore(accountId: String): StoreInformation {
        val store = storeRepository.findByAccountId(accountId)
            ?: throw StoreNotFoundException("Store with account id $accountId not found")
        val fiscalInformation = fiscalInformationRepository.findByAccountId(accountId)
            ?: throw StoreNotFoundException("Fiscal information with account id $accountId not found")

        return StoreInformation(store, fiscalInformation)
    }

    fun getStores(): List<Store> {
        return storeRepository.findStores()
    }

    fun getStoreByName(storeName: String): Store? {
        return storeRepository.findByStoreName(storeName)
    }

    fun getStoresByCategory(category: String): List<Store> {
        return storeRepository.findStoresByCategory(category)
    }
}