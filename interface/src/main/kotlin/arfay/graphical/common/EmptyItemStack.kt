package arfay.graphical.common

import org.bukkit.*
import org.bukkit.enchantments.*
import org.bukkit.inventory.*
import org.bukkit.inventory.meta.*
import org.bukkit.material.*

object EmptyItemStack : ItemStack(Material.AIR) {
   override fun getData() = null
   override fun setData(data: MaterialData?) = Unit
   
   override fun getTypeId() = 0
   override fun setTypeId(type: Int) = Unit
   
   override fun getDurability() = 0.toShort()
   override fun setDurability(durability: Short) = Unit
   
   override fun getType() = Material.AIR
   override fun setType(type: Material?) = Unit
   
   override fun getMaxStackSize() = 0
   override fun getAmount() = 0
   override fun setAmount(amount: Int) = Unit
   
   override fun getItemMeta() = null
   override fun setItemMeta(itemMeta: ItemMeta?) = false
   override fun hasItemMeta() = false
   
   override fun getEnchantmentLevel(ench: Enchantment?) = 0
   override fun addEnchantments(enchantments: MutableMap<Enchantment, Int>?) = Unit
   override fun getEnchantments() = emptyMap<Enchantment, Int>()
   override fun addEnchantment(ench: Enchantment?, level: Int) = Unit
   override fun addUnsafeEnchantments(enchantments: MutableMap<Enchantment, Int>?) = Unit
   override fun addUnsafeEnchantment(ench: Enchantment?, level: Int) = Unit
   override fun containsEnchantment(ench: Enchantment?) = false
   override fun removeEnchantment(ench: Enchantment?) = 0
   
   override fun clone() = EmptyItemStack
   
   override fun equals(other: Any?) = other is EmptyItemStack
   override fun isSimilar(stack: ItemStack?) = stack is EmptyItemStack
}

