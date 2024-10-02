package app.boboc.demogateway

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import java.util.*
import javax.crypto.SecretKey

object JWTUtils {
    fun generate(claims: Map<String, Any>, secretKey: SecretKey): String {
        val now = Date()
        return Jwts.builder()
            .claims(claims)
            .signWith(secretKey)
            .expiration(Date(now.time + 24 * 60 * 60 * 1000))
            .issuedAt(now)
            .compact()
    }

    fun validateJWT(token: String, key: SecretKey): Jws<Claims>? {
        return try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
        } catch (e: Exception){
            throw e
        }
    }
}