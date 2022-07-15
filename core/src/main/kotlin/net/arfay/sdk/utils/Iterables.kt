package net.arfay.sdk.utils

/**
 * Returns the size of this iterable.
 */
val <T> Iterable<T>.size get() = if (this is Collection) size else count()


