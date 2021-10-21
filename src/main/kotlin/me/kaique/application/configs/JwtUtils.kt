package me.kaique.application.configs

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.util.Date

class JwtUtils(private val secret: String) {

    fun validToken(token: String): Boolean {
        val claims = getClaims(token)
        if (claims != null) {
            val accountId = claims.subject
            val expirationDate = claims.expiration
            val now = Date(System.currentTimeMillis())
            return accountId != null && expirationDate != null && now.before(expirationDate)
        }
        return false
    }

    fun getAccountId(token: String): String? {
        val claims = getClaims(token)
        return claims?.subject
    }

    private fun getClaims(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }
    }
}