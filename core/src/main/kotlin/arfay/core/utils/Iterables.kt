package arfay.core.utils

/**
 * Returns the size of this iterable.
 */
val <T> Iterable<T>.size get() = if (this is Collection) size else count()

/**
 * Checks if this iterable is empty.
 */
fun Iterable<*>.isEmpty(): Boolean {
	if (this is Collection<*>) return this.isEmpty()
	return !iterator().hasNext()
}

/**
 * Checks if this iterable is empty.
 */
fun Iterable<*>.isNotEmpty(): Boolean {
	if (this is Collection<*>) return !this.isEmpty()
	return iterator().hasNext()
}

/**
 * Process only specified placeholder in this iterable object.
 */
fun Iterable<String>.process(value: Pair<String, Any>): List<String> = map {
	it.process(value)
}

/**
 * Process all specifieds placeholders in this iterable object.
 */
fun Iterable<String>.process(vararg values: Pair<String, Any>): List<String> = map {
	it.process(*values)
}

/**
 * Process all specifieds placeholders in this iterable object.
 */
fun Iterable<String>.process(values: Map<String, Any>): List<String> = map {
	it.process(values)
}
