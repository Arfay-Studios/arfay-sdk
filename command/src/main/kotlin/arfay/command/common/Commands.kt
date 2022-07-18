package arfay.command.common

import arfay.command.CommandRegistry
import arfay.command.Instructor
import org.bukkit.command.*

/**
 * Converts this command to a instructor.
 */
fun Command.toInstructor() = Instructor(name) {
	this.aliases = aliases
	this.description = description
	this.label = label
	this.permission = permission
	this.permissionMessage = permissionMessage
}

/**
 * Finds a instructor command that's is created from [Instructor] by [name].
 */
fun findCommand(name: String): Instructor? {
	return CommandRegistry[name]
}
