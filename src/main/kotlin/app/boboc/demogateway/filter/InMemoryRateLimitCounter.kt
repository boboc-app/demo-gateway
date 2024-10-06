package app.boboc.demogateway.filter

import java.time.Duration
import java.time.Instant

class InMemoryRateLimitCounter : RateLimitCounter<InMemoryRateLimitCounter.Key> {

    private val counter = mutableMapOf<Key, Int>()
    data class Key(
        val userId: String,
        val path: String,
    )

    data class Value(
        val initialRequestedAt: Instant = Instant.now(),
        var count : Int = 0
    )

    override fun increase(id: Key, recoverIn: Duration, increaseValue: Int) {
        TODO("Not yet implemented")
    }

}
