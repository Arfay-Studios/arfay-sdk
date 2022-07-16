@file:Suppress("NOTHING_TO_INLINE")

package arfay.serializer.common

import net.arfay.sdk.strings.*
import net.arfay.sdk.utils.*
import net.arfay.sdk.utils.Slot
import net.minecraft.server.v1_8_R3.*
import org.bukkit.*
import org.bukkit.craftbukkit.v1_8_R3.inventory.*
import org.bukkit.inventory.*
import org.bukkit.inventory.ItemStack
import kotlin.reflect.*
import java.io.*
import java.lang.reflect.*
import java.util.*

/**
 * Writes the given string in this data output.
 */
inline fun DataOutput.writeString(value: String) {
   writeUTF(value)
}

/**
 * Writes the given [location] value to this data output.
 */
fun DataOutput.writeLocation(location: Location) {
   writeUTF(location.world.name)
   writeDouble(location.x)
   writeDouble(location.y)
   writeDouble(location.z)
   writeFloat(location.yaw)
   writeFloat(location.pitch)
}


/**
 * Writes the given [block] value to this data output.
 */
fun DataOutput.writeBlock(block: org.bukkit.block.Block) {
   writeUTF(block.world.name)
   writeInt(block.x)
   writeInt(block.y)
   writeInt(block.z)
}

/**
 * Writes the given [value] class to this data output.
 */
fun DataOutput.writeClass(value: KClass<*>) = writeUTF(value.qualifiedName ?: value.toString())

/**
 * Writes the given [uuid] value to this data output.
 */
fun DataOutput.writeUUID(uuid: UUID) {
   writeLong(uuid.mostSignificantBits)
   writeLong(uuid.leastSignificantBits)
}

/**
 * Writes the given [item] value to this data output.
 */
fun DataOutput.writeItem(item: ItemStack) {
   writeCompound(item.saveTo())
}

/**
 * Loads a tag compound from this data input stream.
 */
fun DataInput.readCompound(tag: NBTTagCompound = NBTTagCompound()): NBTTagCompound {
   return tag.apply {
      loadCompoundMethod.invoke(this, this@readCompound, 0, NBTReadLimiter.a)
   }
}

internal val loadCompoundMethod: Method by lazy {
   NBTTagCompound::class.java.getDeclaredMethod("load")
}

/**
 * Writes a tag compound to this data output stream.
 */
fun DataOutput.writeCompound(tag: NBTTagCompound) {
   writeCompoundMethod.invoke(tag, this)
}

internal val writeCompoundMethod: Method by lazy {
   NBTTagCompound::class.java.getDeclaredMethod("write")
}

/**
 * Writes the given [slot] value to this data output.
 */
fun DataOutput.writeSlot(slot: Slot) {
   writeItem(slot.item)
   writeByte(slot.slot)
}

/**
 * Writes the given [inv] value to this data output.
 */
fun DataOutput.writeInventory(inv: Inventory) {
   writeUTF(inv.title)
   writeByte(inv.size)
   writeByte(inv.filterNotNull().size)
   for (i in 0 until inv.size) {
      val item = inv.getItem(i) ?: continue
      writeSlot(item slot i)
   }
}

/**
 * Writes the given [enum] value to this data output.
 */
fun DataOutput.writeEnum(enum: Enum<*>) = writeUTF(enum.name)

/**
 * Reads a string value from this data input.
 */
inline fun DataInput.readString(): String {
   return readUTF()
}

/**
 * Reads a location value from this data input.
 */
fun DataInput.readLocation(): Location {
   return Location(
      readUTF().toWorld(), readDouble(), readDouble(), readDouble(), readFloat(), readFloat()
   )
}


/**
 * Reads a block value from this data input.
 */
fun DataInput.readBlock(): org.bukkit.block.Block {
   return readUTF().toWorld().getBlockAt(readInt(), readInt(), readInt())
}

/**
 * Reads a class value from this data input.
 */
fun DataInput.readClass(): KClass<*> = Class.forName(readUTF()).kotlin

/**
 * Reads a uuid value from this data input.
 */
fun DataInput.readUUID(): UUID = UUID(readLong(), readLong())

/**
 * Reads a item stack value from this data input.
 */
fun DataInput.readItem(): ItemStack = CraftItemStack.asBukkitCopy(NMSItem.createStack(readCompound()))

/**
 * Reads a slot value from this data input.
 */
fun DataInput.readSlot(): Slot = readItem() slot readByte().toInt()

/**
 * Reads a inventory value from this data input.
 */
fun DataInput.readInventory(): Inventory {
   val title = readUTF()
   val size = readByte().toInt()
   val inv = Bukkit.createInventory(null, size, title)
   val count = readByte()
   for (i in 0 until count) readSlot().give(inv)
   return inv
}

/**
 * Reads a enum value from this data input.
 */
inline fun <reified T : Enum<T>> DataInput.readEnum(): T = enumValueOf(readUTF())
