/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.misc

import com.mojang.authlib.*
import com.mojang.authlib.properties.*
import net.minecraft.server.v1_8_R3.*
import org.apache.commons.lang3.*
import org.bukkit.*
import org.bukkit.Material
import org.bukkit.block.banner.*
import org.bukkit.craftbukkit.v1_8_R3.entity.*
import org.bukkit.craftbukkit.v1_8_R3.inventory.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.*
import org.bukkit.inventory.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.*
import org.bukkit.material.*
import org.bukkit.potion.*
import java.util.*
import java.util.function.*
import java.util.function.Function

class ItemBuilder(var item: ItemStack) {
   
   var meta: ItemMeta = item.itemMeta
   
   constructor(material: Material) : this(ItemStack(material))
   constructor(data: MaterialData, amount: Int = 1) : this(data.toItemStack(amount))
   
   fun amount(amount: Int) = apply {
      item.amount = amount
   }
   
   fun name(name: String) = apply {
      meta.displayName = ChatColor.translateAlternateColorCodes(
         '&',
         name
      )
      
      item.itemMeta = meta
   }
   
   fun lore(lore: List<String>) = apply {
      return lore(lore, false)
   }
   
   fun lore(lore: List<String>, override: Boolean) = apply {
      val lines = lore.map { ChatColor.translateAlternateColorCodes('&', it) }.toMutableList()
      
      if (!override) {
         val oldLines = meta.lore
         
         if (oldLines !== null && oldLines.isNotEmpty()) {
            lines.addAll(0, oldLines)
         }
      }
      
      meta.lore = lines
      item.itemMeta = meta
   }
   
   fun durability(durability: Int) = apply {
      item.durability = durability.toShort()
      
      item.itemMeta = meta
   }
   
   fun data(data: Int) = apply {
      item.data = MaterialData(
         item.type,
         data.toByte()
      )
      
      item.itemMeta = meta
   }
   
   fun patterns(patterns: Array<Pattern>) = apply {
      if (item.type === Material.BANNER) {
         (meta as BannerMeta).patterns = patterns.toList()
      }
      
      item.itemMeta = meta
   }
   
   fun glowing(glowing: Boolean = true) = apply {
      if (item.type === Material.GOLDEN_APPLE) {
         durability(if (glowing) 1 else 0)
      }
      
      if (item.enchantments.isEmpty() && glowing) {
         createNBT {
            it.set("ench", NBTTagList())
         }
      } else if (!glowing) removeNBT("ench")
   }
   
   fun clearFlags(flags: Array<ItemFlag>) = apply {
      meta.removeItemFlags(*flags)
      item.itemMeta = meta
   }
   
   fun flags(flags: Array<ItemFlag>) = apply {
      meta.addItemFlags(*flags)
      
      item.itemMeta = meta
   }
   
   fun enchant(enchantment: Enchantment, level: Int) = apply {
      item.addUnsafeEnchantment(
         enchantment,
         level
      )
   }
   
   fun enchant(enchantment: Enchantment) = apply {
      item.addUnsafeEnchantment(enchantment, 1)
   }
   
   fun enchantments(enchs: Map<Enchantment, Int>) = apply {
      item.addUnsafeEnchantments(enchs)
   }
   
   fun enchantments(enchantments: Array<Enchantment>, level: Int) = apply {
      enchantments.forEach { enchant(it, level) }
   }
   
   fun enchantments(vararg enchantments: Enchantment) = apply {
      enchantments.forEach { enchant(it) }
   }
   
   fun clearEnchantment(enchantment: Enchantment) = apply {
      item.removeEnchantment(enchantment)
   }
   
   fun clearEnchantments(vararg enchantments: Enchantment) = apply {
      enchantments.forEach { clearEnchantment(it) }
   }
   
   fun effect(potionEffect: PotionEffect, overwrite: Boolean) = apply {
      if (meta is PotionMeta) {
         (meta as PotionMeta).addCustomEffect(potionEffect, overwrite)
      }
      
      item.itemMeta = meta
   }
   
   fun color(color: Color) = apply {
      if (ArrayUtils.contains(
            arrayOf(
               Material.LEATHER_HELMET,
               Material.LEATHER_CHESTPLATE,
               Material.LEATHER_LEGGINGS,
               Material.LEATHER_BOOTS,
            ),
            item.type
         )
      ) {
         (meta as LeatherArmorMeta).color = color
      }
      
      item.itemMeta = meta
   }
   
   fun color(baseColor: DyeColor) = apply {
      if (item.type === Material.BANNER) {
         (meta as BannerMeta).baseColor = baseColor
      }
      
      item.itemMeta = meta
   }
   
   fun clearColor() = apply {
      if (ArrayUtils.contains(
            arrayOf(
               Material.LEATHER_HELMET,
               Material.LEATHER_CHESTPLATE,
               Material.LEATHER_LEGGINGS,
               Material.LEATHER_BOOTS,
            ),
            item.type
         )
      ) {
         (meta as LeatherArmorMeta).color = null
      }
      
      if (item.type === Material.BANNER) {
         (meta as BannerMeta).baseColor = null
      }
      
      item.itemMeta = meta
   }
   
   fun skullOwner(owner: String) = apply {
      if (item.type == Material.SKULL_ITEM && item.durability == 3.toShort()) {
         val skullMeta = meta as SkullMeta
         
         skullMeta.owner = owner
         
         item.itemMeta = skullMeta
      }
   }
   
   fun skull(player: Player) = apply {
      if (item.type == Material.SKULL_ITEM && item.durability == 3.toShort()) {
         val skullMeta = meta as SkullMeta
         
         skullMeta.owner = "CustomSkull"
         
         val playerProfile = (player as CraftPlayer).profile
         val gameProfile = GameProfile(UUID.randomUUID(), null)
         
         gameProfile.properties.putAll(
            "textures",
            playerProfile.properties["textures"]
         )
         
         val fieldProfile = skullMeta::class.java.getDeclaredField("profile")
         
         fieldProfile.isAccessible = true
         
         fieldProfile.set(skullMeta, gameProfile)
         
         item.itemMeta = skullMeta
      }
   }
   
   fun skull(texture: String) = apply {
      if (item.type == Material.SKULL_ITEM && item.durability == 3.toShort()) {
         val skullMeta = meta as SkullMeta
         skullMeta.owner = "CustomSkull"
         
         val gameProfile = GameProfile(UUID.randomUUID(), null)
         gameProfile.properties.put("textures", Property("textures", texture, null))
         
         val fieldProfile = skullMeta::class.java.getDeclaredField("profile")
         fieldProfile.isAccessible = true
         fieldProfile.set(skullMeta, gameProfile)
         
         item.itemMeta = skullMeta
      }
   }
   
   fun createNBT(consumer: Consumer<NBTTagCompound>): NBTTagCompound {
      val nmsCopy = CraftItemStack.asNMSCopy(item)
      
      val compound = if (nmsCopy.hasTag()) nmsCopy.tag else NBTTagCompound()
      
      consumer.accept(compound)
      
      nmsCopy.tag = compound
      
      meta = CraftItemStack.asBukkitCopy(nmsCopy).itemMeta
      
      item.itemMeta = meta
      
      return compound
   }
   
   fun <T> createNBT(function: Function<NBTTagCompound, T>): T {
      val nmsCopy = CraftItemStack.asNMSCopy(item)
      
      val compound = if (nmsCopy.hasTag()) nmsCopy.tag else NBTTagCompound()
      
      val t = function.apply(compound)
      
      nmsCopy.tag = compound
      
      item = CraftItemStack.asBukkitCopy(nmsCopy)
      
      return t
   }
   
   private fun removeNBT(key: String) {
      val nmsCopy = CraftItemStack.asNMSCopy(item)
      
      val compound = if (nmsCopy.hasTag()) nmsCopy.tag else NBTTagCompound()
      
      return compound.remove(key)
   }
   
   private fun ItemStack.hasNBT(key: String): Boolean {
      val nmsCopy = CraftItemStack.asNMSCopy(this)
      
      val compound = if (nmsCopy.hasTag()) nmsCopy.tag else NBTTagCompound()
      
      return compound.hasKey(key)
   }
   
   fun NBT(key: String, value: NBTBase) = apply {
      createNBT { it.set(key, value) }
   }
   
   fun NBT(key: String, value: Int) = apply {
      createNBT { it.setInt(key, value) }
   }
   
   fun NBT(key: String, value: Boolean) = apply {
      createNBT { it.setBoolean(key, value) }
   }
   
   fun NBT(key: String, value: Long) = apply {
      createNBT { it.setLong(key, value) }
      
      return this
   }
   
   fun NBT(key: String, value: String) = apply {
      createNBT { it.setString(key, value) }
   }
   
   fun NBTTagString(key: String): String? {
      TODO("Não implementado")
   }
   
   fun NBTTagInt(key: String): Int? {
      TODO("Não implementado")
   }
   
   fun NBTTagLong(key: String): Long? {
      TODO("Não implementado")
   }
   
   fun NBTTagDouble(key: String): Double? {
      TODO("Não implementado")
   }
   
   fun NBTTagBoolean(key: String): Boolean? {
      TODO("Não implementado")
   }
   
   fun NBTTagList(key: String): NBTTagList? {
      TODO("Não implementado")
   }
   
   fun build(): ItemStack {
      this.item.itemMeta = meta
      return this.item
   }
   
}
