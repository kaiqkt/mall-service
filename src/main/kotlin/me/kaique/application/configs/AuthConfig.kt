package me.kaique.application.configs

import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Context
import io.javalin.http.ForbiddenResponse
import me.kaique.application.configs.Constants.ACCOUNT_ID
import me.kaique.application.configs.Constants.BEARER_TOKEN_NAME
import me.kaique.application.configs.Constants.HEADER_TOKEN_NAME

internal enum class Roles : Role {
    ANYONE, SERVICE, CUSTOMER
}

class AuthConfig(private val serviceToken: String, private val jwtUtils: JwtUtils) {
    fun configure(app: Javalin) {
        app.config.accessManager { handler, ctx, permittedRoles ->
            val token = getAuthorizationHeader(ctx)
            val role = verifyRole(ctx, token)
            permittedRoles.takeIf { !it.contains(role) }?.apply { throw ForbiddenResponse() }

            handler.handle(ctx)
        }
    }

    private fun getAuthorizationHeader(ctx: Context): String? = ctx.header(HEADER_TOKEN_NAME)?.trim()

    private fun verifyRole(ctx: Context, token: String?): Role {

        token?.let {
            if (token.startsWith(BEARER_TOKEN_NAME)) {
                val parsedToken = it.substring(7)
                if (jwtUtils.validToken(parsedToken)) {
                    ctx.attribute(ACCOUNT_ID, jwtUtils.getAccountId(parsedToken))
                    return Roles.CUSTOMER
                }

                return Roles.ANYONE
            } else if (token == serviceToken) {
                return Roles.SERVICE
            }
        }

        return Roles.ANYONE
    }
}

class AuthenticationFailedException(message: String): Exception(message)