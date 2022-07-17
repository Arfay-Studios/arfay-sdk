@file:Suppress("NOTHING_TO_INLINE")

package arfay.core.utils

import org.bukkit.*
import org.bukkit.entity.*
import java.util.*

/**
 * Calls [UUID.fromString] to create a uuid from [string].
 */
inline fun uuid(string: String): UUID = UUID.fromString(string)

/**
 * Creates a new random [uuid].
 */
inline fun randomUUID(): UUID = UUID.randomUUID()

/**
 * Calls [UUID.nameUUIDFromBytes] to create a uuid from [bytes].
 */
inline fun uuid(bytes: ByteArray): UUID = UUID.nameUUIDFromBytes(bytes)

/**
 * Creates a [UUID] from [name] to locate a offline player.
 */
fun offlineUUID(name: String) = uuid("OfflinePlayer:$name".toByteArray())

/**
 * Parses this string as a UUID
 */
inline fun String.toUUID(): UUID = UUID.fromString(this)

/**
 * Parses this UUID to a [Player].
 */
inline fun UUID.toPlayer(): Player = Bukkit.getPlayer(this)

/**
 * Parses this UUID to a [OfflinePlayer].
 */
inline fun UUID.toOfflinePlayer(): OfflinePlayer = Bukkit.getOfflinePlayer(this)

/**
 * Parses this UUID to a [World].
 */
inline fun UUID.toWorld(): World = Bukkit.getWorld(this)
