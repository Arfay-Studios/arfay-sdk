package arfay.core.utils

import arfay.core.extensions.*
import arfay.core.misc.*
import org.bukkit.event.*
import org.bukkit.event.block.*
import org.bukkit.event.player.*
import org.bukkit.plugin.*
import kotlin.reflect.*

/**
 * Calls this event.
 */
fun <T : Event> T.call(): T {
   pluginManager.callEvent(this)
   return this
}

/**
 * Returns if the action of this interact event is any type of left click.
 */
val PlayerInteractEvent.isLeftClick: Boolean
   get() = action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK

/**
 * Returns if the action of this interact event is any type of right click.
 */
val PlayerInteractEvent.isRightClick: Boolean
   get() = action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK

/**
 * Register a event listener with the specified event in generic type in this server.
 */
inline fun <T : Event> Listener.listening(
   type: KClass<T>,
   plugin: Plugin,
   priority: EventPriority = EventPriority.NORMAL,
   ignoreCancelled: Boolean = false,
   crossinline action: T.() -> Unit,
) = pluginManager.registerEvent(type.java, this, priority, { _, event ->
   if (type.isInstance(event))
      action(event.cast())
}, plugin, ignoreCancelled)


/**
 * Register a event listener with the specified event in generic type in this server.
 */
inline fun <reified T : Event> Listener.listening(
   plugin: Plugin,
   priority: EventPriority = EventPriority.NORMAL,
   ignoreCancelled: Boolean = false,
   noinline action: T.() -> Unit,
) = listening(T::class, plugin, priority, ignoreCancelled, action)

/**
 * Register a event listener with the specified event in generic type in this server.
 */
inline fun <reified T : Event> Plugin.listening(
   priority: EventPriority = EventPriority.NORMAL,
   ignoreCancelled: Boolean = false,
   noinline action: T.() -> Unit,
) = StandardListener().listening(this, priority, ignoreCancelled, action)


/**
 * Register a event listener with the specified event in generic type in this server.
 */
fun <T : Event> Listener.subscribe(
   type: KClass<T>,
   plugin: Plugin,
   priority: EventPriority = EventPriority.NORMAL,
   ignoreCancelled: Boolean = false,
   action: T.() -> Unit,
) = pluginManager.registerEvent(type.java, this, priority, { _, event ->
   if (type.isInstance(event))
      action(event.cast())
}, plugin, ignoreCancelled)


/**
 * Register a event listener with the specified event in generic type in this server.
 */
inline fun <reified T : Event> Listener.subscribe(
   plugin: Plugin,
   priority: EventPriority = EventPriority.NORMAL,
   ignoreCancelled: Boolean = false,
   noinline action: T.() -> Unit,
) = subscribe(T::class, plugin, priority, ignoreCancelled, action)


/**
 * Register a event listener with the specified event in generic type in this server.
 */
inline fun <reified T : Event> Plugin.subscribe(
   priority: EventPriority = EventPriority.NORMAL,
   ignoreCancelled: Boolean = false,
   noinline action: T.() -> Unit,
) = StandardListener().subscribe(this, priority, ignoreCancelled, action)
