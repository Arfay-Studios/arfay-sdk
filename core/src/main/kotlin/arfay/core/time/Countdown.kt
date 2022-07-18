package arfay.core.time

import arfay.core.utils.*
import kotlinx.serialization.*

/**
 * A countdown class represents a duration that never expires.
 * This is a skeletal class implementation of [StartedTime].
 */
@Serializable
data class Countdown(override var startMillis: Long = currentMillis) : StartedTime {
	override fun toString() = lifetime.formatTime()
}
