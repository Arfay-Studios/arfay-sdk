package arfay.sdk.utils

import kotlinx.coroutines.*
import kotlin.coroutines.*

/**
 * Creates a new [CoroutineScope] by the given [context].
 */
fun scope(context: CoroutineContext = EmptyCoroutineContext) = CoroutineScope(context)

/**
 * Launches a new coroutine running forever until their cancellation.
 */
fun CoroutineScope.always(
   context: CoroutineContext = EmptyCoroutineContext,
   start: CoroutineStart = CoroutineStart.DEFAULT,
   block: suspend CoroutineScope.() -> Unit
) = launch(context, start) {
   while (true) {
      block()
   }
}

/**
 * Launches a new coroutine running forever while [block] returns true.
 */
fun CoroutineScope.until(
   context: CoroutineContext = EmptyCoroutineContext,
   start: CoroutineStart = CoroutineStart.DEFAULT,
   block: suspend CoroutineScope.() -> Boolean
) = launch(context, start) {
   while (block()) {
   }
}

/**
 * Launches a new coroutine running repeated amount of times.
 */
fun CoroutineScope.repeated(
   repeat: Int,
   context: CoroutineContext = EmptyCoroutineContext,
   start: CoroutineStart = CoroutineStart.DEFAULT,
   block: suspend CoroutineScope.() -> Unit
) = launch(context, start) {
   repeat(repeat) {
      block()
   }
}

