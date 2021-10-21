package me.kaique.domain.gateways.persistence

import me.kaique.domain.entities.FiscalInformation
import me.kaique.domain.entities.Account

interface FiscalInformationRepository {
    fun save(fiscalInformation: FiscalInformation, account: Account)
    fun findByAccountId(accountId: String): FiscalInformation?
    fun findByCnpj(cnpj: String): FiscalInformation?
    fun findByCpf(cpf: String): FiscalInformation?
}