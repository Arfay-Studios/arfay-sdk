package walkmc.entity

import org.bukkit.entity.*

/**
 * A metadata annotation used to know and register what entity type
 * the class annotated with this annotation is.
 *
 * [type] - the entity type model. You can use modify this to alter the entity model
 *
 * [name] - the name of the custom entity. A empty name will be the class name.
 */
annotation class Mob(val type: EntityType, val name: String = "")
