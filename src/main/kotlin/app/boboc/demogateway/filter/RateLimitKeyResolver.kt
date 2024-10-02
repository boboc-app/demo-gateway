package app.boboc.demogateway.filter

import app.boboc.demogateway.JWTUtils
import app.boboc.demogateway.config.JwtProperties
import app.boboc.demogateway.repository.UserRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class RateLimitKeyResolver(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository
) : KeyResolver {

    private fun getAccessToken(authorizationHeader: String): String {
        return authorizationHeader.split("Bearer ").last()
    }

    private fun getUserIdFromJWT(token: String): String {
        return JWTUtils.validateJWT(token, jwtProperties.getSecretKey())
            ?.run {
                payload["userId"] as String
            } ?: throw Exception("User not found")
    }

    override fun resolve(exchange: ServerWebExchange): Mono<String> {
        return mono {
            getAccessToken(
                exchange.request.headers.getFirst(HttpHeaders.AUTHORIZATION)
                    ?: throw Exception("Authorization header missing")
            ).run {
                getUserIdFromJWT(this)
            }.also {
                if(!userRepository.existsById(it)){
                    throw Exception("User not found")
                }
            }
        }
    }
}
