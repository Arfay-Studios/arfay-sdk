/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.extensions

import com.mojang.authlib.GameProfile
import net.arfay.sdk.chat.createChatComponent
import net.arfay.sdk.message.sendPacket
import net.md_5.bungee.api.chat.TextComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutChat
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.util.BlockIterator
import java.util.*

inline val Player.craftHandler get() = this as CraftPlayer

/**
 * Shortcut for getting the UUID of this player.
 */
inline val OfflinePlayer.uuid: UUID get() = uniqueId

/**
 * Returns the ip of this player.
 */
inline val Player.ip: String get() = address.hostName

/**
 * Makes the player says a message in the chat.
 */
inline fun Player.say(message: String) = chat(message)

/**
 * Gives a item to the player inventory.
 */
inline fun Player.giveItem(item: ItemStack): Map<Int, ItemStack> = inventory.addItem(item)

/**
 * Clears completely the inventory of this inventory, including the armor content.
 */
fun Player.clearInventory() {
    inventory.armorContents = null
    inventory.clear()
}

/**
 * Gets the current held slot of this player.
 */
inline var Player.heldSlot: Int
    get() = inventory.heldItemSlot
    set(value) {
        inventory.heldItemSlot = value
    }

/**
 * Sends a action bar to this player.
 */
fun Player.action(message: Any) {
    sendPacket(PacketPlayOutChat(createChatComponent(message.toString()), 2.toByte()))
}

/**
 * Plays a sound for this player.
 */
fun Player.playSound(sound: Sound, volume: Float, pitch: Float) {
    playSound(location, sound, volume, pitch)
}

/**
 * Logs a text component to this player.
 */
inline fun Player.log(component: TextComponent) = spigot().sendMessage(component)

/**
 * Gets the target block of this player. This not classifies AIR blocks as target.
 * @see getTargetBlockAir
 */
fun Player.getTargetBlock(range: Int): Block? {
    val iter = BlockIterator(this, range)
    var lastBlock = iter.next()
    while (iter.hasNext()) {
        lastBlock = iter.next()
        if (lastBlock.type === Material.AIR) continue
        break
    }
    return lastBlock
}

/**
 * Gets the target block of this player. This classifies AIR blocks as target.
 */
fun Player.getTargetBlockAir(range: Int): Block {
    val iterator = BlockIterator(this, range)
    var current = 0
    var lastBlock = iterator.next()
    while (current++ < range || iterator.hasNext())
        lastBlock = iterator.next()
    return lastBlock
}

