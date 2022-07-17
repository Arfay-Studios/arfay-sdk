package arfay.sdk.utils

import com.google.common.collect.*
import net.arfay.sdk.extensions.*
import net.arfay.sdk.strings.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.*
import org.bukkit.Material
import org.bukkit.craftbukkit.v1_8_R3.inventory.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.material.*
import java.lang.reflect.*
import java.util.*

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

inline fun item(
    material: MaterialData,
    amount: Int = 1,
    meta: ItemMeta.() -> Unit = {}
): ItemStack = material.toItemStack(amount).meta(meta)

inline fun item(
    material: Material,
    amount: Int = 1,
    data: Short = 0,
    meta: ItemMeta.() -> Unit = {}
): ItemStack = ItemStack(material, amount, data).meta(meta)

inline fun <reified T : ItemMeta> metadataItem(
    material: Material,
    amount: Int = 1,
    data: Short = 0,
    meta: T.() -> Unit
): ItemStack = ItemStack(material, amount, data).meta(meta)

inline fun <reified T : ItemMeta> ItemStack.meta(
    block: T.() -> Unit
): ItemStack = apply {
    itemMeta = (itemMeta as? T)?.apply(block) ?: itemMeta
}

fun ItemStack.displayName(displayName: String): ItemStack = meta<ItemMeta> {
    this.displayName = displayName
}

fun ItemStack.lore(lore: List<String>): ItemStack = meta<ItemMeta> {
    this.lore = lore
}

inline fun Material.asItemStack(
    amount: Int = 1,
    data: Short = 0,
    meta: ItemMeta.() -> Unit = {}
): ItemStack = item(this, amount, data, meta)

fun Material.asMaterialData(
    data: Byte = 0
) = MaterialData(this, data)

inline fun MaterialData.toItemStack(
    amount: Int = 1,
    meta: ItemMeta.() -> Unit = {}
) = toItemStack(amount).meta(meta)

/**
 * get head from base64
 */
val gameProfileClass: Class<*> by lazy { Class.forName("com.mojang.authlib.GameProfile") }
val propertyClass: Class<*> by lazy { Class.forName("com.mojang.authlib.properties.Property") }
val getPropertiesMethod: Method by lazy { gameProfileClass.getMethod("getProperties") }
val gameProfileConstructor: Constructor<out Any> by lazy { gameProfileClass.getConstructor(UUID::class.java, String::class.java) }
val propertyConstructor: Constructor<out Any> by lazy { propertyClass.getConstructor(String::class.java, String::class.java) }

fun headFromBase64(base64: String): ItemStack {
    val item = ItemStack(Material.SKULL_ITEM, 1, 3)
    val meta = item.itemMeta as SkullMeta

    val profile = gameProfileConstructor.newInstance(UUID.randomUUID(), null as String?)
    val properties = getPropertiesMethod.invoke(profile) as Multimap<Any, Any>
    properties.put("textures", propertyConstructor.newInstance("textures", base64))

    val profileField = meta.javaClass.getDeclaredField("profile").apply { isAccessible = true }
    profileField.set(meta, profile)

    return item.apply { itemMeta = meta }
}

inline val Material.isPickaxe: Boolean get() = name.endsWith("PICKAXE")
inline val Material.isSword: Boolean get() = name.endsWith("SWORD")
inline val Material.isAxe: Boolean get() = name.endsWith("_AXE")
inline val Material.isSpade: Boolean get() = name.endsWith("SPADE")
inline val Material.isHoe: Boolean get() = name.endsWith("HOE")
inline val Material.isOre: Boolean get() = name.endsWith("ORE")
inline val Material.isIngot: Boolean get() = name.endsWith("INGOT")
inline val Material.isDoor: Boolean get() = name.endsWith("DOOR")
inline val Material.isMinecart: Boolean get() = name.endsWith("MINECART")
inline val Material.isWater: Boolean get() = this == Material.WATER || this == Material.STATIONARY_WATER
inline val Material.isLava: Boolean get() = this == Material.LAVA || this == Material.STATIONARY_LAVA
