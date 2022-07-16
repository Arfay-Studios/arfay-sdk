@file:Suppress("NOTHING_TO_INLINE")

/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.extensions

import org.bukkit.*
import org.bukkit.entity.*
import java.util.*

/**
 * Returns a random [UUID].
 */
inline fun randomUUID(): UUID = UUID.randomUUID()

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
