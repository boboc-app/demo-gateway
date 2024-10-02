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
    companion object{
        const val CONFIGURATION_PROPERTY_NAME = "boboc-rate-limiter"
        const val ACCOUNT_RATE_LIMIT_HEADER = "X-Boboc-Ratelimt"
    }

    data class Config(
        val a: String
    )

    override fun isAllowed(routeId: String?, id: String?): Mono<RateLimiter.Response> {
        TODO("Not yet implemented")
    }


}