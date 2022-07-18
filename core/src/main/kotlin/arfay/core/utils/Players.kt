package arfay.core.utils

import arfay.core.chat.*
import arfay.core.misc.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.*
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.craftbukkit.v1_8_R3.entity.*
import org.bukkit.entity.*
import org.bukkit.util.*
import java.util.*

/**
 * Gets this player as [CraftPlayer].
 */
inline val Player.craftHandler: CraftPlayer
   get() = this as CraftPlayer

/**
 * Gets the NMS handler of this player.
 */
inline val Player.handler: EntityPlayer
   get() = craftHandler.handle

/**
 * Gets the player connection of this player.
 */
val Player.connection: PlayerConnection
   get() = handler.playerConnection

/**
 * Returns the head of this player.
 */
val OfflinePlayer.head: org.bukkit.inventory.ItemStack
   get() = ItemBuilder(Materials.PLAYER_SKULL.toItem()).skullOwner(name).build()

/**
 * Sends a packet to this player.
 */
fun Player.sendPacket(packet: Packet<out PacketListener>) {
   connection.sendPacket(packet)
}

/**
 * Sends an action bar to this player.
 */
fun Player.actionBar(message: String) {
   sendPacket(PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"$message\"}"), 2.toByte()))
}

/**
 * Sends a title to this player.
 */
fun Player.title(
	title: String = "",
	subtitle: String = "",
	fadeIn: Int = 20,
	stay: Int = 40,
	fadeOut: Int = 20,
) {
	sendPacket(PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, createChatComponent(title)))
	sendPacket(PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, createChatComponent(subtitle)))
	sendPacket(PacketPlayOutTitle(fadeIn, stay, fadeOut))
}

/**
 * Plays a sound for this player.
 */
fun Player.playSound(sound: Sound, volume: Float = 1f, pitch: Float = 1f) {
	playSound(location, sound, volume, pitch)
}

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

inline val Player.uuid: UUID get() = uniqueId

inline val OfflinePlayer.uuid: UUID get() = uniqueId

fun Player.clearInventory() {
    inventory.armorContents = null
    inventory.clear()
}

