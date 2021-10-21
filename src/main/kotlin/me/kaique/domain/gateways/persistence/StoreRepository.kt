package me.kaique.domain.gateways.persistence

import me.kaique.domain.entities.Store
import me.kaique.domain.entities.Account

interface StoreRepository {
    fun save(store: Store, account: Account)
    fun findByStoreName(storeName: String): Store?
    fun findByAccountId(accountId: String): Store?
}