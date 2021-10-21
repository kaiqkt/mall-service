package me.kaique.resources.singleregistry.clients

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isSuccessful
import me.kaique.domain.entities.Account
import me.kaique.domain.exceptions.CommunicationException
import me.kaique.resources.helper.contentType
import me.kaique.resources.singleregistry.entities.AccountResponse
import org.eclipse.jetty.http.HttpHeader
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class SingleRegistryClient(
    private val serviceUrl: String,
    private val serviceSecret: String,
    private val mapper: ObjectMapper
) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun getAccountById(accountId: String): Account? {
        log.info("Finding account $accountId")

       return Fuel.get("$serviceUrl/account/$accountId")
            .header(
                mapOf(
                    HttpHeader.AUTHORIZATION.toString() to serviceSecret,
                    HttpHeader.CONTENT_TYPE.toString() to contentType("get_account", 1),
                )
            )
            .response().let { (_, response, result) ->
                when {
                    response.isSuccessful -> {
                        jacksonObjectMapper().readValue<AccountResponse>(result.get()).let {
                            log.info("Account $accountId found")
                            Account(email = it.email, phone = it.phone)
                        }
//                            mapper.readValue<AccountResponse>(result.get()).let {
//                                log.info("Account $accountId found")
//                                Account(email = it.email, phone = it.phone)
//                            }
                    }
                    response.statusCode == HttpStatus.NOT_FOUND_404 -> {
                        log.info("Account $accountId not found")
                        null
                    }
                    else -> {
                        log.info(response.responseMessage)
                        throw CommunicationException("An error occurred while registering user")
                    }
                }
            }
    }
}


