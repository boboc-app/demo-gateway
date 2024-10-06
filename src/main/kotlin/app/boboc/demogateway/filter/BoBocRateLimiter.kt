package app.boboc.demogateway.filter

import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter
import org.springframework.cloud.gateway.support.ConfigurationService
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class BoBocRateLimiter(
    configurationService: ConfigurationService,
) : AbstractRateLimiter<BoBocRateLimiter.Config>(
    Config::class.java,
    CONFIGURATION_PROPERTY_NAME,
    configurationService
) {
    companion object {
        const val CONFIGURATION_PROPERTY_NAME = "boboc-rate-limiter"
        const val ACCOUNT_RATE_LIMIT_HEADER = "X-Boboc-Ratelimt"
        const val ACCOUNT_CAPACITY_HEADER = "X-Boboc-Capacity"
        const val DEFAULT_RECOVER_IN = 3600
        const val DEFAULT_MAXIMUM_CAPACITY = 10
    }

    data class Config(
        val maximumCapacity: Int? = null,
        val recoverIn: Int = DEFAULT_RECOVER_IN,
    )

    fun getHeader(config: Config, currentCount: Int): Map<String, Int> {
        return mapOf(
            ACCOUNT_CAPACITY_HEADER to (config.maximumCapacity ?: DEFAULT_MAXIMUM_CAPACITY),
            ACCOUNT_RATE_LIMIT_HEADER to currentCount
        )
    }

    override fun isAllowed(routeId: String, id: String): Mono<RateLimiter.Response> {
        println(id)

        config[routeId]

        TODO("Not yet implemented")
    }


}