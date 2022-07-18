package arfay.core.utils

import java.math.*

/**
 * Clamps [value] with [min] and [max].
 */
fun between(min: Int, value: Int, max: Int) = min(min, max(value, max))

/**
 * Clamps [value] with [min] and [max].
 */
fun between(min: Long, value: Long, max: Long) = min(min, max(value, max))

/**
 * Clamps [value] with [min] and [max].
 */
fun between(min: Float, value: Float, max: Float) = min(min, max(value, max))

/**
 * Clamps [value] with [min] and [max].
 */
fun between(min: Double, value: Double, max: Double) = min(min, max(value, max))

/**
 * Verifies if this double has any decimals.
 */
inline val Double.hasDecimals get() = this % 1 == 0.0

/**
 * Verifies if this float has any decimals.
 */
inline val Float.hasDecimals get() = this % 1 == 0f

/**
 * Returns the percentage needed to the [min] needs to get in [max].
 */
fun percentage(min: Number, max: Number) = min.toDouble() * 100 / max.toDouble()

/**
 * Plus a percent of this actual number.
 */
infix fun Number.plusPercent(percent: Double): Double = toDouble() + (toDouble() / 100 * percent)

/**
 * Subtract a percent of this actual number.
 */
infix fun Number.minusPercent(percent: Double): Double = toDouble() - (toDouble() / 100 * percent)

/**
 * Multiplies a percent of this actual number.
 */
infix fun Number.timesPercent(percent: Double): Double = toDouble() * (toDouble() / 100 * percent)

/**
 * Divides a percent of this actual number.
 */
infix fun Number.divPercent(percent: Double): Double = toDouble() / (toDouble() / 100 * percent)

/**
 * Rounds this double to a max decimal places.
 */
infix fun Double.decimals(decimals: Int): Double =
   BigDecimal(this).setScale(decimals, RoundingMode.HALF_UP).toDouble()

/**
 * Rounds this float to a max decimal places.
 */
infix fun Float.decimals(decimals: Int): Float =
   BigDecimal(toDouble()).setScale(decimals, RoundingMode.HALF_UP).toFloat()

/**
 * Returns a positive representation of this byte value if the value is negative.
 */
inline val Byte.positive get() = +this

/**
 * Returns a negative representation of this byte value if the value is positive.
 */
inline val Byte.negative get() = -this

/**
 * Returns a positive representation of this short value if the value is negative.
 */
inline val Short.positive get() = +this

/**
 * Returns a negative representation of this short value if the value is positive.
 */
inline val Short.negative get() = -this

/**
 * Returns a positive representation of this int value if the value is negative.
 */
inline val Int.positive get() = +this

/**
 * Returns a negative representation of this int value if the value is positive.
 */
inline val Int.negative get() = -this

/**
 * Returns a positive representation of this long value if the value is negative.
 */
inline val Long.positive get() = +this

/**
 * Returns a negative representation of this long value if the value is positive.
 */
inline val Long.negative get() = -this

/**
 * Returns a positive representation of this float value if the value is negative.
 */
inline val Float.positive get() = +this

/**
 * Returns a negative representation of this float value if the value is positive.
 */
inline val Float.negative get() = -this

/**
 * Returns a positive representation of this double value if the value is negative.
 */
inline val Double.positive get() = +this

/**
 * Returns a negative representation of this double value if the value is positive.
 */
inline val Double.negative get() = -this
