package app.boboc.demogateway.filter

import java.time.Duration

interface RateLimitCounter<K> {
    fun increase(id : K, recoverIn: Duration, increaseValue : Int = 1) : Int
}
