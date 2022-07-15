@file:Suppress("NOTHING_TO_INLINE")

package net.arfay.sdk.extensions

inline fun <A, B> A.cast(): B = this as B
