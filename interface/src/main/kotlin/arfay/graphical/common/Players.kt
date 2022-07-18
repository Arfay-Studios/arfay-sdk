@file:Suppress("NOTHING_TO_INLINE")

package arfay.graphical.common

import org.bukkit.entity.*
import walkmc.graphical.*

/**
 * Makes this player access the specified interface
 */
inline fun Player.accessInterface(graphical: IGraphical) = graphical.access(this)

/**
 * Makes this player uncess the current opened interface.
 */
fun Player.uncessInterface() = interfaceOrNull()?.uncess()

/**
 * Returns the current opened interface of this player or null.
 */
fun Player.interfaceOrNull(): IGraphical? = openInventory.topInventory.interfaceOrNull()
inline fun <reified T : IGraphical> Player.graphical(): T? = openInventory.topInventory.graphical()
