package arfay.sdk.utils

import net.arfay.sdk.extensions.*
import net.arfay.sdk.strings.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.*
import org.bukkit.craftbukkit.v1_8_R3.inventory.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.material.*

/**
 * Gets the display name of this item.
 */
var ItemStack.name: String
   get() = itemMeta.displayName
   set(value) {
      meta<ItemMeta> {
         displayName = value
      }
   }

/**
 * Gets the lore of this item.
 */
var ItemStack.lore: MutableList<String>
   get() = itemMeta.lore
   set(value) {
      meta<ItemMeta> {
         lore = value
      }
   }

/**
 * Gets the material of this item.
 */
var ItemStack.material: Materials
   get() = Materials.from(data)
   set(value) {
      typeId = value.id
      durability = value.subdata.toShort()
   }

/**
 * Gets the skull data of this item.
 */
var ItemStack.skull: String
   get() = (itemMeta as? SkullMeta)?.head ?: ""
   set(value) {
      meta<SkullMeta> {
         head = value
      }
   }

/**
 * Creates a NMS copy item stack from this item.
 */
fun ItemStack.toNMS(): NMSItem = CraftItemStack.asNMSCopy(this)

/**
 * Creates a NMS copy item stack from this item or null if the item cannot be converted.
 */
fun ItemStack.toNMSOrNull(): NMSItem? = CraftItemStack.asNMSCopy(this)

/**
 * Saves this item stack to the given [tag].
 */
fun ItemStack.saveTo(tag: NBTTagCompound = NBTTagCompound()): NBTTagCompound {
   val copy = toNMSOrNull() ?: return tag
   copy.save(tag)
   return tag
}

/**
 * Sets the specified name to this item stack.
 */
fun ItemStack.name(name: String): ItemStack {
   return displayName(name)
}

/**
 * Inserts the specified lore to this item stack.
 */
fun ItemStack.lore(lines: Iterable<String>): ItemStack {
   return meta<ItemMeta> {
      this.lore = lines.toMutableList()
   }
}

/**
 * Inserts the specified lore to this item stack.
 */
fun ItemStack.lore(vararg lines: String): ItemStack {
   return meta<ItemMeta> {
      this.lore = lines.toMutableList()
   }
}

/**
 * Inserts the specified enchantments to this item stack.
 */
fun ItemStack.enchantments(vararg enchantments: Pair<org.bukkit.enchantments.Enchantment, Int>): ItemStack {
   addUnsafeEnchantments(enchantments.toMap())
   return this
}

/**
 * Sets the specified amount of this item stack.
 */
fun ItemStack.amount(amount: Int): ItemStack {
   this.amount = amount
   return this
}

/**
 * Sets the specified durability of this item stack.
 */
fun ItemStack.durability(durability: Int): ItemStack {
   this.durability = durability.toShort()
   return this
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore.
 */
fun ItemStack.process(model: ItemStack, value: Pair<String, Any>) = apply {
   name(model.name.process(value))
   lore(model.lore.process(value))
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore.
 */
fun ItemStack.process(model: ItemStack, vararg value: Pair<String, Any>) = apply {
   name(model.name.process(*value))
   lore(model.lore.process(*value))
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore.
 */
fun ItemStack.process(model: ItemStack, value: Map<String, Any>) = apply {
   name(model.name.process(value))
   lore(model.lore.process(value))
}

/**
 * Process only specified placeholder in this item stack.
 */
fun ItemStack.process(value: Pair<String, Any>) = apply {
   name(name.process(value))
   lore(lore.process(value))
}

/**
 * Process all specifieds placeholders in this item stack.
 */
fun ItemStack.process(vararg values: Pair<String, Any>) = apply {
   name(name.process(*values))
   lore(lore.process(*values))
}

/**
 * Process all specifieds placeholders in this item stack.
 */
fun ItemStack.process(values: Map<String, Any>) = apply {
   name(name.process(values))
   lore(lore.process(values))
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore and returns a copy of them.
 */
fun ItemStack.processCopy(model: ItemStack, value: Pair<String, Any>) =
   clone().process(model, value)

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore and returns a copy of them.
 */
fun ItemStack.processCopy(model: ItemStack, vararg value: Pair<String, Any>): ItemStack =
   clone().process(model, *value)

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore and returns a copy of them.
 */
fun ItemStack.processCopy(model: ItemStack, value: Map<String, Any>): ItemStack =
   clone().process(model, value)

/**
 * Process only specified placeholder in this item stack and returns a copy of them.
 */
fun ItemStack.processCopy(value: Pair<String, Any>): ItemStack = clone().process(value)

/**
 * Process all specifieds placeholders in this item stack and returns a copy of them.
 */
fun ItemStack.processCopy(vararg values: Pair<String, Any>): ItemStack = clone().process(*values)

/**
 * Process all specifieds placeholders in this item stack and returns a copy of them.
 */
fun ItemStack.processCopy(values: Map<String, Any>): ItemStack = clone().process(values)

/**
 * Copies and changes this item stack to the specified [block] function.
 */
inline fun ItemStack.copy(block: ItemStack.() -> Unit) = clone().apply(block)

/**
 * Changes this item stack material type by the specified [material].
 */
fun ItemStack.changeToMaterial(material: Materials): ItemStack {
   this.material = material
   return this
}

/**
 * Changes this item stack to the specified head representation of the owner [name].
 */
fun ItemStack.changeToHeadOwner(name: String): ItemStack {
   material = Materials.PLAYER_SKULL
   meta<SkullMeta> {
      owner = name
   }
   return this
}

/**
 * Changes this item stack to the specified head representation of the owner [player].
 */
fun ItemStack.changeToHeadOwner(player: OfflinePlayer): ItemStack {
   material = Materials.PLAYER_SKULL
   meta<SkullMeta> {
      owner = player.name
   }
   return this
}

/**
 * Copies and changes this item stack material type by the specified [material].
 */
fun ItemStack.copyToMaterial(material: Materials) = copy {
   this.material = material
}

/**
 * Copies and changes this item stack to the specified head representation of the owner [name].
 */
fun ItemStack.copyToHeadOwner(name: String) = copy {
   material = Materials.PLAYER_SKULL
   meta<SkullMeta> {
      owner = name
   }
}

/**
 * Copies and changes this item stack to the specified head representation of the owner [player].
 */
fun ItemStack.copyToHeadOwner(player: OfflinePlayer) = copy {
   material = Materials.PLAYER_SKULL
   meta<SkullMeta> {
      owner = player.name
   }
}

/**
 * Creates a new item stack by the specified model and amount.
 */
fun item(model: ItemStack, amount: Int = 1): ItemStack = model.clone().amount(amount)

/**
 * Creates a new item stack by the specified model and name.
 */
fun item(model: ItemStack, name: String, amount: Int = 1): ItemStack {
   return model.clone().name(name).amount(amount)
}

/**
 * Creates a new item stack by the specified model and lore.
 */
fun item(model: ItemStack, lore: List<String>, amount: Int = 1): ItemStack {
   return model.clone().lore(lore).amount(amount)
}

/**
 * Creates a new item stack by the specified model, name and lore.
 */
fun item(model: ItemStack, name: String, lore: List<String>, amount: Int = 1): ItemStack {
   return model.clone().amount(amount).meta<ItemMeta> {
      displayName = name
      this.lore = lore
   }
}

/**
 * Creates a new item stack by the specified material.
 */
fun item(material: Materials, amount: Int = 1): ItemStack = material.toItem(amount)

/**
 * Creates a new item stack by the specified material and name.
 */
fun item(material: Materials, name: String, amount: Int = 1): ItemStack {
   return item(material, amount).name(name)
}

/**
 * Creates a new item stack by the specified material and lore.
 */
fun item(material: Materials, lore: List<String>, amount: Int = 1): ItemStack {
   return item(material, amount).lore(lore)
}

/**
 * Creates a new item stack by the specified material, name and lore.
 */
fun item(material: Materials, name: String, lore: List<String>, amount: Int = 1): ItemStack {
   return item(material, amount).meta<ItemMeta> {
      displayName = name
      this.lore = lore
   }
}

/**
 * Creates a new item stack by the specified material data and name.
 */
fun item(material: MaterialData, name: String, amount: Int = 1): ItemStack {
   return material.toItemStack(amount).name(name)
}

/**
 * Creates a new item stack by the specified material data and lore.
 */
fun item(material: MaterialData, lore: List<String>, amount: Int = 1): ItemStack {
   return material.toItemStack(amount).lore(lore)
}

/**
 * Creates a new item stack by the specified material data, name and lore.
 */
fun item(material: MaterialData, name: String, lore: List<String>, amount: Int = 1): ItemStack {
   return material.toItemStack(amount).meta<ItemMeta> {
      displayName = name
      this.lore = lore
   }
}
