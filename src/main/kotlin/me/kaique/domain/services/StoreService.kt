package me.kaique.domain.services

import me.kaique.domain.entities.FiscalType
import me.kaique.domain.entities.StoreInformation
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
            validationService.validateLegal(fiscalInformation.cnpj!!, store.storeName, store.accountId)
        } else {
            validationService.validateIndividual(fiscalInformation.cpf!!, store.storeName, store.accountId)
        }

        val account = singleRegistryService.findAccountById(store.accountId)
        storeRepository.save(store, account)
        fiscalInformationRepository.save(fiscalInformation, account)
        log.info("Legal store with id ${store.id} created successfully")

        return storeInformation
    }
}