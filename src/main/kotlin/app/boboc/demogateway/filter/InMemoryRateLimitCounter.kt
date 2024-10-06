package app.boboc.demogateway.filter

import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class InMemoryRateLimitCounter : RateLimitCounter<InMemoryRateLimitCounter.Key> {

    private val counter = mutableMapOf<Key, Value>()

    data class Key(
        val userId: String,
        val routerId: String,
    )

    data class Value(
        val initialRequestedAt: Instant = Instant.now(),
        var count: Int = 0
    ) {
        fun isExpired(now: Instant, recoverIn: Duration): Boolean {
            return initialRequestedAt.epochSecond - now.epochSecond >= recoverIn.toSeconds()
        }
    }

    override fun increase(id: Key, recoverIn: Duration, increaseValue: Int): Int {
        val now = Instant.now()

        counter[id]?.apply {
            if (isExpired(now, recoverIn)) {
                counter[id] = Value(now, increaseValue)
            } else {
                count += increaseValue
            }
        } ?: counter.put(id, Value(now, increaseValue))

        return counter[id]!!.count
    }

}
