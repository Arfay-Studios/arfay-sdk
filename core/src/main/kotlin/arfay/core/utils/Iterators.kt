package arfay.core.utils

import com.google.common.collect.*

operator fun <T> Iterator<T>.plus(other: Iterator<T>): MutableIterator<T> = Iterators.concat(this, other)
fun <T> Iterator<T>.add(other: Iterator<T>): MutableIterator<T> = Iterators.concat(this, other)

fun <T> Iterator<T>.toList() = asSequence().toList()
fun <T> Iterator<T>.toMutableList() = asSequence().toMutableList()
fun <T> Iterator<T>.toSet() = asSequence().toSet()
fun <T> Iterator<T>.toHashSet() = asSequence().toHashSet()
fun <T> Iterator<T>.toMutableSet() = asSequence().toMutableSet()

