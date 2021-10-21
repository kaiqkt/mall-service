package me.kaique.domain.services

import me.kaique.domain.exceptions.AlreadyExistsException
import me.kaique.domain.gateways.persistence.FiscalInformationRepository
import me.kaique.domain.gateways.persistence.StoreRepository

class ValidationService(
    private val storeRepository: StoreRepository,
    private val fiscalInformationRepository: FiscalInformationRepository
) {
    fun validateLegal(cnpj: String, storeName: String, accountId: String) {
        val existsByCnpj = fiscalInformationRepository.findByCnpj(cnpj) != null
        if (existsByCnpj) {
            throw AlreadyExistsException("Cnpj: $this already used")
        }

        validateStore(storeName, accountId)
    }

    fun validateIndividual(cpf: String, storeName: String, accountId: String) {
        val existsByCpf = fiscalInformationRepository.findByCpf(cpf) != null
        if (existsByCpf) {
            throw AlreadyExistsException("Cpf: $this already used")
        }

        validateStore(storeName, accountId)
    }

    private fun validateStore(storeName: String, accountId: String) {
        val existByUserName = storeRepository.findByStoreName(storeName) != null
        val existsByAccountId = storeRepository.findByAccountId(accountId) != null

        if (existByUserName) {
            throw AlreadyExistsException("Store name: $this already used")
        }

        if (existsByAccountId) {
            throw AlreadyExistsException("Account id: $this already has registered store")
        }
    }
}