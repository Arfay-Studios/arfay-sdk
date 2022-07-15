package net.arfay.sdk.utils

import java.util.*

/**
 * Executes [action] for every element and removes them.
 *
 * This will start from the first element to the last element.
 */
inline fun <T> MutableList<T>.forEachRemove(action: (T) -> Unit) {
   for (value in this) {
      action(value)
      remove(value)
   }
}

/**
 * Executes [action] for every element and removes them.
 *
 * This will start from the last element to the first element.
 */
inline fun <T> MutableList<T>.forEachPoll(action: (T) -> Unit) {
   var n = size
   while (n >= 0) {
      val element = get(n)
      action(element)
      remove(element)
      n--
   }
}

/**
 * Executes [action] for every element and removes them.
 *
 * This will start from the first element to the last element.
 */
inline fun <T> Queue<T>.forEachRemove(action: (T) -> Unit) {
   for (value in this) {
      action(value)
      remove(value)
   }
}

/**
 * Executes [action] for every element and removes them.
 *
 * This will start from the last element to the first element.
 */
inline fun <T> Queue<T>.forEachPoll(action: (T) -> Unit) {
   while (isNotEmpty()) action(poll())
}
