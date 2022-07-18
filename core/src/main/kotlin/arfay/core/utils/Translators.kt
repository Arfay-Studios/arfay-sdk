package arfay.core.utils

import org.bukkit.*
import org.bukkit.enchantments.*

/**
 * Translates a gamemode to the default portuguese language.
 */
val GameMode.translated: String
   get() = when (this) {
      GameMode.SPECTATOR -> "Espectador"
      GameMode.ADVENTURE -> "Aventura"
      GameMode.CREATIVE -> "Criativo"
      else -> "Sobrevivência"
   }

/**
 * Translates a enchantment to the default portuguese language.
 */
val Enchantment.translated: String
   get() = when (this) {
      Enchantment.PROTECTION_ENVIRONMENTAL -> "Proteção"
      Enchantment.PROTECTION_EXPLOSIONS -> "Proteção contra explosões"
      Enchantment.PROTECTION_FIRE -> "Proteção contra fogo"
      Enchantment.PROTECTION_FALL -> "Peso pena"
      Enchantment.PROTECTION_PROJECTILE -> "Proteção contra projéteis"
      Enchantment.DIG_SPEED -> "Eficiência"
      Enchantment.ARROW_DAMAGE -> "Força"
      Enchantment.ARROW_FIRE -> "Chamas"
      Enchantment.ARROW_INFINITE -> "Infinidade"
      Enchantment.ARROW_KNOCKBACK -> "Impacto"
      Enchantment.DAMAGE_ALL -> "Afiação"
      Enchantment.DAMAGE_ARTHROPODS -> "Ruína dos artrópodes"
      Enchantment.DAMAGE_UNDEAD -> "Julgamento"
      Enchantment.KNOCKBACK -> "Repulsão"
      Enchantment.DURABILITY -> "Inquebrável"
      Enchantment.FIRE_ASPECT -> "Aspecto flamejante"
      Enchantment.SILK_TOUCH -> "Toque suave"
      Enchantment.OXYGEN -> "Respiração"
      Enchantment.THORNS -> "Espinhos"
      Enchantment.LOOT_BONUS_MOBS -> "Pilhagem"
      Enchantment.LOOT_BONUS_BLOCKS -> "Fortuna"
      Enchantment.LUCK -> "Sorte do mar"
      Enchantment.LURE -> "Isca"
      Enchantment.WATER_WORKER -> "Afinidade aquática"
      Enchantment.DEPTH_STRIDER -> "Passos profundos"
      else -> name
   }
