package me.kaique.application.configs

import io.javalin.http.Context
import me.kaique.application.configs.Constants.ACCOUNT_ID

fun Context.getAccountIdByToken() = attribute<String>(ACCOUNT_ID) ?: throw AuthenticationFailedException("Missing account id")


fun Context.notAcceptable() {
    status(406)
    json("Invalid value for Content-Type header")
    contentType("application/json")
}

fun Context.getContentTypeWithoutCharset(): String? = this.contentType()?.split(";")?.first()