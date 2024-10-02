package app.boboc.demogateway.filter

import app.boboc.demogateway.repository.UserRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class VerifyUserFilter(
    private val userRepository: UserRepository
) : GatewayFilter {
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        mono {
            chain.filter(exchange)
        }

        TODO("Not yet implemented")
    }
}