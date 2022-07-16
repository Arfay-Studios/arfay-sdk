@file:Suppress("NOTHING_TO_INLINE")

package arfay.sdk.utils

import com.soywiz.kmem.*
import net.jafama.*
import kotlin.math.*

val SQRT_2 = sqrt(2.0)

inline fun cos(n: Double) = FastMath.cosQuick(n)
inline fun cos(n: Int) = FastMath.cosQuick(n.toDouble())
inline fun cos(n: Float) = FastMath.cosQuick(n.toDouble())

inline fun acos(n: Double) = FastMath.acos(n)
inline fun acos(n: Int) = FastMath.acos(n.toDouble())
inline fun acos(n: Float) = FastMath.acos(n.toDouble())

inline fun acosh(n: Double) = FastMath.acosh(n)
inline fun acosh(n: Int) = FastMath.acosh(n.toDouble())
inline fun acosh(n: Float) = FastMath.acosh(n.toDouble())

inline fun sin(n: Double) = FastMath.sinQuick(n)
inline fun sin(n: Int) = FastMath.sinQuick(n.toDouble())
inline fun sin(n: Float) = FastMath.sinQuick(n.toDouble())

inline fun asin(n: Double) = FastMath.asin(n)
inline fun asin(n: Int) = FastMath.asin(n.toDouble())
inline fun asin(n: Float) = FastMath.asin(n.toDouble())

inline fun asinh(n: Double) = FastMath.asinh(n)
inline fun asinh(n: Int) = FastMath.asinh(n.toDouble())
inline fun asinh(n: Float) = FastMath.asinh(n.toDouble())

inline fun tan(n: Double) = FastMath.tan(n)
inline fun tan(n: Int) = FastMath.tan(n.toDouble())
inline fun tan(n: Float) = FastMath.tan(n.toDouble())

inline fun sqrt(n: Int) = sqrt(n.toDouble())
inline fun sqrt(n: Float) = sqrt(n.toDouble())

inline fun pow(n: Double, power: Double) = FastMath.powQuick(n, power)
inline fun pow(n: Int, power: Double) = FastMath.powQuick(n.toDouble(), power)
inline fun pow(n: Float, power: Double) = FastMath.powQuick(n.toDouble(), power)

inline fun exp(n: Double) = FastMath.expQuick(n)
inline fun exp(n: Int) = FastMath.expQuick(n.toDouble())
inline fun exp(n: Float) = FastMath.expQuick(n.toDouble())

inline fun log(n: Double) = FastMath.logQuick(n)
inline fun log(n: Int) = FastMath.logQuick(n.toDouble())
inline fun log(n: Float) = FastMath.logQuick(n.toDouble())

inline fun floorInt(n: Double) = floor(n).toInt()
inline fun floorInt(n: Float) = floor(n).toInt()

inline fun floorLong(n: Double) = floor(n).toLong()
inline fun floorLong(n: Float) = floor(n).toLong()

inline fun absInt(n: Double) = abs(n).toInt()
inline fun absInt(n: Float) = abs(n).toInt()

inline fun squared(n: Int) = n * n
inline fun squared(n: Double) = n * n
inline fun squared(n: Float) = n * n

inline fun min(a: Int, b: Int) = if (a <= b) a else b
inline fun min(a: Long, b: Long) = if (a <= b) a else b
inline fun min(a: Float, b: Float) = if (a <= b) a else b
inline fun min(a: Double, b: Double) = if (a <= b) a else b

inline fun max(a: Int, b: Int) = if (a >= b) a else b
inline fun max(a: Long, b: Long) = if (a >= b) a else b
inline fun max(a: Float, b: Float) = if (a >= b) a else b
inline fun max(a: Double, b: Double) = if (a >= b) a else b

