package me.kaique.application.web.controller

import io.javalin.http.Context
import me.kaique.application.configs.getAccountIdByToken
import me.kaique.application.web.dto.IndividualRegisterRequest
import me.kaique.application.web.dto.LegalRegisterRequest
import me.kaique.application.web.dto.toDomain
import me.kaique.application.web.dto.toResponse
import me.kaique.application.web.ext.isCnpjValid
import me.kaique.application.web.ext.isCpfValid
import me.kaique.domain.services.StoreService
import org.eclipse.jetty.http.HttpStatus

class StoreController(private val storeService: StoreService) {

    fun registerLegalStore(ctx: Context) {
        ctx.bodyValidator<LegalRegisterRequest>()
            .check({ it.cnpj.isCnpjValid() })
            .get().also { request ->
                val accountId = ctx.getAccountIdByToken()
                storeService.create(request.toDomain(accountId = accountId)).apply {
                    ctx.json(this.toResponse()).status(HttpStatus.OK_200)
                }
            }
    }

    fun registerIndividualStore(ctx: Context) {
        ctx.bodyValidator<IndividualRegisterRequest>()
            .check({ it.cpf.isCpfValid() })
            .get().also { request ->
                val accountId = ctx.getAccountIdByToken()
                storeService.create(request.toDomain(accountId = accountId)).apply {
                    ctx.json(this.toResponse()).status(HttpStatus.OK_200)
                }
            }
    }

    fun getAllStores(ctx: Context) {
        storeService.getStores().map { it.toResponse() }.apply {
            ctx.json(this).status(HttpStatus.OK_200)
        }
    }

    fun getStoreByName(ctx: Context) {
        ctx.pathParam<String>("storeName").get().also {
            storeService.getStoreByName(it)?.apply {
                ctx.json(this.toResponse()).status(HttpStatus.OK_200)
            }
        }
    }

    fun getStoresByCategory(ctx: Context) {
        ctx.pathParam<String>("category").get().also { param ->
            storeService.getStoresByCategory(param).map { it.toResponse() }.apply {
                ctx.json(this).status(HttpStatus.OK_200)
            }
        }
    }

    fun getStore(ctx: Context) {
        storeService.getStore(ctx.getAccountIdByToken()).apply {
            ctx.json(this.toResponse()).status(HttpStatus.OK_200)
        }
    }
}