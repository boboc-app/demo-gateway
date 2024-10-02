package app.boboc.demogateway.config

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.crypto.SecretKey

@ConfigurationProperties("jwt")
data class JwtProperties(
    private val secretKey: String,
){
    private val _secretKey : SecretKey = Keys.hmacShaKeyFor(secretKey.toByteArray())

    fun getSecretKey() : SecretKey = _secretKey
}