package arfay.sdk.misc

import org.bukkit.event.*

/**
 * A abstract implementation of [Event].
 */
abstract class EventBase(isAsync: Boolean = false) : Event(isAsync) {
	override fun getHandlers() = HANDLERS
	
	companion object {
		private val HANDLERS = HandlerList()
		
		@JvmStatic
		fun getHandlerList() = HANDLERS
	}
}

/**
 * A abstract implementation of [Event] with [Cancellable].
 */
abstract class CancellableEvent(isAsync: Boolean = false) : EventBase(isAsync), Cancellable {
	private var isCancelled: Boolean = false
	
	override fun setCancelled(cancelled: Boolean) {
		isCancelled = cancelled
	}
	
	override fun isCancelled(): Boolean {
		return isCancelled
	}
	
	override fun getHandlers() = HANDLERS
	
	companion object {
		private val HANDLERS = HandlerList()
		
		@JvmStatic
		fun getHandlerList() = HANDLERS
	}
}

/**
 * A standard implementation of [Listener]. This is used to create new instances of listeners.
 */
class StandardListener : Listener
