package arfay.core.time

import arfay.core.utils.*
import kotlinx.serialization.*
import kotlin.time.*

/**
 * A cooldown class represents a duration that can be expired.
 * Note: [duration] is in milliseconds.
 */
@Serializable
open class Delay(val duration: Long, override var startMillis: Long = currentMillis) : StartedTime {
	constructor(duration: Duration) : this(duration.inWholeMilliseconds)
	
	/**
	 * Returns when this cooldown will finish.
	 */
   open val finishIn = startMillis + duration
   
	/**
	 * Returns the time left to expires this cooldown.
	 */
	open val expiresIn: Long
		get() = startMillis + duration - currentMillis
	
	/**
	 * Verifies if this cooldown has expired.
	 */
   open val isExpired: Boolean
		get() = expiresIn <= 0
	
	override fun toString(): String {
		return expiresIn.formatTime()
	}
}

/**
 * Represents a empty delay.
 */
object EmptyDelay : Delay(0, 0) {
   override val finishIn: Long = 0
   override val expiresIn: Long = 0
   override val isExpired: Boolean = true
   override fun toString(): String = "0s"
}


