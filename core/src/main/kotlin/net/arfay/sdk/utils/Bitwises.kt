@file:Suppress("NOTHING_TO_INLINE")

package net.arfay.sdk.utils

// BYTE

inline infix fun Byte.shl(other: Int) = (toInt() shl other).toByte()
inline infix fun Byte.shl(other: Number) = shl(other.toInt())

inline infix fun Byte.shr(other: Int) = (toInt() shr other).toByte()
inline infix fun Byte.shr(other: Number) = shr(other.toInt())

inline infix fun Byte.ushr(other: Int) = (toInt() ushr other).toByte()
inline infix fun Byte.ushr(other: Number) = ushr(other.toInt())

inline infix fun Byte.or(other: Int) = (toInt() or other).toByte()
inline infix fun Byte.or(other: Number) = or(other.toInt())

inline infix fun Byte.xor(other: Int) = (toInt() xor other).toByte()
inline infix fun Byte.xor(other: Number) = xor(other.toInt())

inline infix fun Byte.and(other: Int) = (toInt() and other).toByte()
inline infix fun Byte.and(other: Number) = and(other.toInt())

// SHORT

inline infix fun Short.shl(other: Int) = (toInt() shl other).toShort()
inline infix fun Short.shl(other: Number) = shl(other.toInt())

inline infix fun Short.shr(other: Int) = (toInt() shr other).toShort()
inline infix fun Short.shr(other: Number) = shr(other.toInt())

inline infix fun Short.ushr(other: Int) = (toInt() ushr other).toShort()
inline infix fun Short.ushr(other: Number) = ushr(other.toInt())

inline infix fun Short.or(other: Int) = (toInt() or other).toShort()
inline infix fun Short.or(other: Number) = or(other.toInt())

inline infix fun Short.xor(other: Int) = (toInt() xor other).toShort()
inline infix fun Short.xor(other: Number) = xor(other.toInt())

inline infix fun Short.and(other: Int) = (toInt() and other).toShort()
inline infix fun Short.and(other: Number) = and(other.toInt())

// INT

inline infix fun Int.shl(other: Number) = shl(other.toInt())

inline infix fun Int.shr(other: Number) = shr(other.toInt())

inline infix fun Int.ushr(other: Number) = ushr(other.toInt())

inline infix fun Int.or(other: Number) = or(other.toInt())

inline infix fun Int.xor(other: Number) = xor(other.toInt())

inline infix fun Int.and(other: Number) = and(other.toInt())

// LONG

inline infix fun Long.shl(other: Number) = shl(other.toInt())

inline infix fun Long.shr(other: Number) = shr(other.toInt())

inline infix fun Long.ushr(other: Number) = ushr(other.toInt())

inline infix fun Long.or(other: Int) = (toInt() or other).toLong()
inline infix fun Long.or(other: Number) = or(other.toInt())

inline infix fun Long.xor(other: Int) = (toInt() xor other).toLong()
inline infix fun Long.xor(other: Number) = xor(other.toInt())

inline infix fun Long.and(other: Int) = (toInt() and other).toLong()
inline infix fun Long.and(other: Number) = and(other.toInt())

// FLOAT

inline infix fun Float.shl(other: Int) = (toInt() shl other).toFloat()
inline infix fun Float.shl(other: Number) = shl(other.toInt())

inline infix fun Float.shr(other: Int) = (toInt() shr other).toFloat()
inline infix fun Float.shr(other: Number) = shr(other.toInt())

inline infix fun Float.ushr(other: Int) = (toInt() ushr other).toFloat()
inline infix fun Float.ushr(other: Number) = ushr(other.toInt())

inline infix fun Float.or(other: Int) = (toInt() or other).toFloat()
inline infix fun Float.or(other: Number) = or(other.toInt())

inline infix fun Float.xor(other: Int) = (toInt() xor other).toFloat()
inline infix fun Float.xor(other: Number) = xor(other.toInt())

inline infix fun Float.and(other: Int) = (toInt() and other).toFloat()
inline infix fun Float.and(other: Number) = and(other.toInt())

// DOUBLE

inline infix fun Double.shl(other: Int) = (toInt() shl other).toDouble()
inline infix fun Double.shl(other: Number) = shl(other.toInt())

inline infix fun Double.shr(other: Int) = (toInt() shr other).toDouble()
inline infix fun Double.shr(other: Number) = shr(other.toInt())

inline infix fun Double.ushr(other: Int) = (toInt() ushr other).toDouble()
inline infix fun Double.ushr(other: Number) = ushr(other.toInt())

inline infix fun Double.or(other: Int) = (toInt() or other).toDouble()
inline infix fun Double.or(other: Number) = or(other.toInt())

inline infix fun Double.xor(other: Int) = (toInt() xor other).toDouble()
inline infix fun Double.xor(other: Number) = xor(other.toInt())

inline infix fun Double.and(other: Int) = (toInt() and other).toDouble()
inline infix fun Double.and(other: Number) = and(other.toInt())
