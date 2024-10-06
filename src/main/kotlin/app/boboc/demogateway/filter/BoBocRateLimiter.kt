package app.boboc.demogateway.filter

import kotlinx.coroutines.reactor.mono
import org.springframework.cloud.gateway.filter.ratelimit.AbstractRateLimiter
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter
import org.springframework.cloud.gateway.support.ConfigurationService
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Duration

@Component
class BoBocRateLimiter(
    configurationService: ConfigurationService,
    private val inMemoryRateLimitCounter: InMemoryRateLimitCounter
) : AbstractRateLimiter<BoBocRateLimiter.Config>(
    Config::class.java,
    CONFIGURATION_PROPERTY_NAME,
    configurationService
) {
    companion object {
        const val CONFIGURATION_PROPERTY_NAME = "boboc-rate-limiter"
        const val ACCOUNT_RATE_LIMIT_HEADER = "X-Boboc-Ratelimt"
        const val ACCOUNT_CAPACITY_HEADER = "X-Boboc-Capacity"
        const val DEFAULT_RECOVER_IN = 3600L
        const val DEFAULT_MAXIMUM_CAPACITY = 10
    }

    data class Config(
        val maximumCapacity: Int? = null,
        val recoverIn: Long = DEFAULT_RECOVER_IN,
    )

    fun getHeader(config: Config, currentCount: Int): Map<String, String> {
        return mapOf(
            ACCOUNT_CAPACITY_HEADER to (config.maximumCapacity ?: DEFAULT_MAXIMUM_CAPACITY).toString(),
            ACCOUNT_RATE_LIMIT_HEADER to currentCount.toString()
        )
    }

    override fun isAllowed(routeId: String, id: String): Mono<RateLimiter.Response> {
        return mono {
            val conf = config[routeId] ?: throw Exception("NotFound configuration for route $routeId")

            val currentCount = inMemoryRateLimitCounter.increase(
                InMemoryRateLimitCounter.Key(id, routeId),
                Duration.ofSeconds((conf.recoverIn))
            )

            RateLimiter.Response(
                false,
                getHeader(conf, currentCount)
            )
        }
    }


}