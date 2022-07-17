/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.core.utils

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.OfflinePlayer
import org.bukkit.World
import org.bukkit.entity.Player

/**
 * Parses this string as [Player]
 */
fun String.toPlayer(): Player = Bukkit.getPlayer(this)

/**
 * Parses this string as [OfflinePlayer]
 */
fun String.toOfflinePlayer(): OfflinePlayer = Bukkit.getOfflinePlayer(this)

/**
 * Parses this string as [World]
 */
fun String.toWorld(): World = Bukkit.getWorld(this)

/**
 * Colorizes this string, replacing '§' to '&'.
 */

fun String.colored(): String {
    return ChatColor.translateAlternateColorCodes('&', this)
}

/**
 * Colorizes this list, replacing '§' to '&'.
 */
fun Iterable<String>.colored(): List<String> {
    return map { it.colored() }
}
/**
 * Reverse colorizes this string, replacing '&' to '§'.
 */
fun String.reverseColorize(): String = replace('&', '§')

/**
 * Uncolorize this string, this is, making this string without colors.
 */
fun String.uncolorize(): String = ChatColor.stripColor(this)

/**
 * Capitalizes this string, making their first char uppercase.
 */
fun String.cap(): String = replaceFirstChar { it.uppercase() }

/**
 * Decapitalizes this string, making their first char lowercase.
 */
fun String.decap(): String = replaceFirstChar { it.lowercase() }

/**
 * Repeat this string by the specified [n] times.
 */
operator fun String.times(n: Int): String = repeat(n)

/**
 * Process a string by the specified [oldValue] to [newValue].
 * This is equals to
 * ```
 * replace(oldValue, "$newValue", true)
 * ```
 */
fun String.process(oldValue: String, newValue: Any): String {
    return replace(oldValue, "$newValue", true)
}

/**
 * Process a string by the specified [oldValue] to [newValue] by lazy.
 */
fun String.process(oldValue: String, newValue: () -> Any): String =
    if (contains(oldValue)) replace(oldValue, "${newValue()}", true) else this

/**
 * Converts this string in a simple text component.
 */
fun String.toSimpleText(): TextComponent = TextComponent(this)

/**
 * Converts this string in a text component.
 */
fun String.toText(): Array<BaseComponent> = TextComponent.fromLegacyText(this)

/**
 * Process only specified placeholder in this string.
 */
fun String.process(value: Pair<String, Any>): String = process(value.first, value.second)

/**
 * Process all specifieds placeholders in this string.
 */
fun String.process(vararg values: Pair<String, Any>): String {
   var processed = this
   for (value in values)
      processed = processed.process(value.first, value.second)
   return processed
}

/**
 * Process all specifieds placeholders in this string.
 */
fun String.process(values: Map<String, Any>): String {
   var processed = this
   for (value in values)
      processed = processed.process(value.key, value.value)
   return processed
}

