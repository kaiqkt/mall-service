package me.kaique.domain.services

import me.kaique.domain.entities.StoreCategory
import me.kaique.domain.exceptions.AlreadyExistsException
import me.kaique.domain.exceptions.InvalidStoreCategoryException
import me.kaique.domain.gateways.persistence.FiscalInformationRepository
import me.kaique.domain.gateways.persistence.StoreRepository

class ValidationService(
    private val storeRepository: StoreRepository,
    private val storeCategoryService: StoreCategoryService,
    private val fiscalInformationRepository: FiscalInformationRepository
) {
    fun validateLegal(cnpj: String, storeName: String, storeCategory: StoreCategory, accountId: String) {
        val existsByCnpj = fiscalInformationRepository.findByCnpj(cnpj) != null
        if (existsByCnpj) {
            throw AlreadyExistsException("Cnpj already used")
        }

        validateStore(storeName, storeCategory, accountId)
    }

    fun validateIndividual(cpf: String, storeName: String, storeCategory: StoreCategory, accountId: String) {
        val existsByCpf = fiscalInformationRepository.findByCpf(cpf) != null
        if (existsByCpf) {
            throw AlreadyExistsException("Cpf already used")
        }

        validateStore(storeName, storeCategory, accountId)
    }

    private fun validateStore(storeName: String, storeCategory: StoreCategory, accountId: String) {
        val existByUserName = storeRepository.findByStoreName(storeName) != null
        val existsByAccountId = storeRepository.findByAccountId(accountId) != null
        val validCategory = storeCategoryService.findByCategoryCode(storeCategory) == null

        if (existByUserName) {
            throw AlreadyExistsException("Store name already used")
        }

        if (existsByAccountId) {
            throw AlreadyExistsException("Account id already has registered store")
        }

        if (validCategory) {
            throw InvalidStoreCategoryException("Store category not found")
        }
    }
}