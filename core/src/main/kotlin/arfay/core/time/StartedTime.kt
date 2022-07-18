package arfay.core.time

import arfay.core.utils.*
import com.soywiz.klock.*
import kotlin.time.*

/**
 * A started time is a time thats holds a start millis, representing the
 * current time millis of this time starteds.
 */
interface StartedTime : Comparable<StartedTime> {
   override fun compareTo(other: StartedTime): Int = lifetime.compareTo(other.lifetime)
   
   /**
    * The started cached millis.
    */
   var startMillis: Long
}

/**
 * Returns the lifetime of this countdown.
 */
inline val StartedTime.lifetime: Long
   get() = currentMillis - startMillis

/**
 * Redefines this started time, this is, reseting their lifetime.
 */
fun StartedTime.redefine() {
   startMillis = currentMillis
}

/**
 * Returns if the lifetime of this started time is more than the specified [timespan]
 */
operator fun StartedTime.compareTo(timespan: TimeSpan): Int = lifetime.compareTo(timespan.millisecondsLong)

/**
 * Returns if the lifetime of this started time is more than the specified [duration]
 */
operator fun StartedTime.compareTo(duration: Duration): Int = lifetime.compareTo(duration.inWholeMilliseconds)
