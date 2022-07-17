package arfay.core.utils

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
