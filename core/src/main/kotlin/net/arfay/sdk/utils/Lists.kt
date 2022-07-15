package net.arfay.sdk.utils

import java.util.*

inline val Collection<*>.lastIndex: Int get() = size - 1

/**
 * Creates a synchronized list from this list.
 */
fun <T> MutableList<T>.sync(): MutableList<T> = Collections.synchronizedList(this)
fun <T> MutableList<T>.swap(from: Int, to: Int) = Collections.swap(this, from, to)
