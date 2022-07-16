@file:Suppress("NOTHING_TO_INLINE")

package arfay.sdk.utils

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
