package me.kaique.resources.singleregistry.gateways

import me.kaique.domain.exceptions.CommunicationException
import me.kaique.domain.exceptions.AccountNotFoundException
import me.kaique.domain.gateways.singleregistry.SingleRegistryService
import me.kaique.resources.singleregistry.clients.SingleRegistryClient
import me.kaique.domain.entities.Account

class SingleRegistryServiceImpl(private val singleRegistryClient: SingleRegistryClient): SingleRegistryService {

    override fun findAccountById(accountId: String): Account {
        try {
            singleRegistryClient.getAccountById(accountId)?.let {
                return it
            }

            throw AccountNotFoundException("Account $accountId not found")
        } catch (e: CommunicationException) {
            throw e
        }
    }
}