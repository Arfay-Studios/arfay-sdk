package arfay.command

import arfay.core.utils.*
import net.md_5.bungee.api.chat.*
import org.bukkit.command.*
import org.bukkit.entity.*

/**
 * A instructor performer is a executor for a instructor.
 */
interface Performer : Argumentable {
	
	/**
	 * Verifies if this sender is a console.
	 */
	val isConsole: Boolean
	
	/**
	 * Verifies if this sender is a player.
	 */
	val isPlayer: Boolean
	
	/**
	 * Returns this sender as console command sender.
	 * If the sender is not a console will be throw a error.
	 */
	val console: ConsoleCommandSender
	
	/**
	 * Returns this sender as player.
	 * If the sender is not a player will be throw a error.
	 */
	val player: Player
}

/**
 * Sends a message to the sender of this instructor performer
 */
fun Performer.log(message: Any) {
	if (isPlayer) player.sendMessage("$message") else console.sendMessage("$message")
}

/**
 * Sends a component message to the sender of this instructor performer
 */
fun Performer.log(message: TextComponent) {
	if (isPlayer) player.spigot().sendMessage(message)
}

/**
 * Sends a action bar message to the sender of this instructor performer
 */
fun Performer.actionBar(message: String) {
	if (isPlayer) player.actionBar(message)
}

/**
 * Sends a title message to the sender of this instructor performer
 */
fun Performer.title(
	title: String = "",
	subtitle: String = "",
	fadeIn: Int = 20,
	stay: Int = 40,
	fadeOut: Int = 20,
) {
	if (isPlayer) player.title(title, subtitle, fadeIn, stay, fadeOut)
}
