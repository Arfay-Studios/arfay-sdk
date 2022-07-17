@file:Suppress("NOTHING_TO_INLINE")

package arfay.core.utils

import com.soywiz.kds.random.*

/**
 * The random instance used to generate random numbers.
 */
val Random = FastRandom()

fun randomByte() = Random.nextInt().toByte()
fun randomByte(max: Byte) = randomInt(max.toInt()).toByte()
fun randomByte(from: Int, until: Int) = randomInt(from, until).toByte()

fun randomShort() = Random.nextInt().toShort()
fun randomShort(max: Short) = randomInt(max.toInt()).toShort()
fun randomShort(from: Int, until: Int) = randomInt(from, until).toShort()

fun randomInt() = Random.nextInt()
fun randomInt(max: Int) = Random.nextInt(max)
fun randomInt(from: Int, until: Int) = Random.nextInt(from, until)

fun randomLong() = Random.nextLong()
fun randomLong(max: Long) = Random.nextLong(max)
fun randomLong(from: Long, until: Long) = Random.nextLong(from, until)

fun randomFloat() = Random.nextFloat()
fun randomFloat(max: Float) = randomDouble(max.toDouble()).toFloat()
fun randomFloat(from: Float, until: Float) = randomDouble(from.toDouble(), until.toDouble()).toFloat()

fun randomDouble() = Random.nextDouble()
fun randomDouble(max: Double) = Random.nextDouble(max)
fun randomDouble(from: Double, until: Double) = Random.nextDouble(from, until)

fun randomBoolean() = Random.nextBoolean()

fun chance(percentage: Double) = randomDouble() * 100 <= percentage
fun chance(percentage: Float) = randomFloat() * 100 <= percentage
fun chance(percentage: Int) = randomInt() * 100 <= percentage
