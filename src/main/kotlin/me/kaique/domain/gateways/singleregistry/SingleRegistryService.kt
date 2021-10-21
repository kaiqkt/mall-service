package me.kaique.domain.gateways.singleregistry

import me.kaique.domain.entities.Account

interface SingleRegistryService {
    fun findAccountById(accountId: String): Account
}