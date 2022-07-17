package arfay.core.utils

import net.minecraft.server.v1_8_R3.*
import org.bukkit.*
import org.bukkit.entity.*
import java.util.*

typealias ParticlePacket = PacketPlayOutWorldParticles

/**
 * A particle enum used to send all particles.
 */
enum class Particle(val type: EnumParticle) {
   
   /**
    * A particle effect which is displayed by exploding tnt and creepers:
    *
    * * It looks like a white cloud
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   EXPLOSION_NORMAL(EnumParticle.EXPLOSION_NORMAL),
   
   /**
    * A particle effect which is displayed by exploding ghast fireballs and wither skulls:
    *
    * * It looks like a gray ball which is fading away
    * * The speed value slightly influences the size of this particle effect
    * * Argument count: 0
    */
   EXPLOSION_LARGE(EnumParticle.EXPLOSION_LARGE),
   
   /**
    * A particle effect which is displayed by exploding tnt and creepers:
    *
    * * It looks like a crowd of gray balls which are fading away
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   EXPLOSION_HUGE(EnumParticle.EXPLOSION_HUGE),
   
   /**
    * A particle effect which is displayed by launching fireworks:
    *
    * * It looks like a white star which is sparkling
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   FIREWORKS_SPARK(EnumParticle.FIREWORKS_SPARK),
   
   /**
    * A particle effect which is displayed by swimming entities and arrows in water:
    *
    * * It looks like a bubble
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   WATER_BUBBLE(EnumParticle.WATER_BUBBLE),
   
   /**
    * A particle effect which is displayed by swimming entities and shaking wolves:
    *
    * * It looks like a blue drop
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   WATER_SPLASH(EnumParticle.WATER_SPLASH),
   
   /**
    * A particle effect which is displayed on water when fishing:
    *
    * * It looks like a blue droplet
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   WATER_WAKE(EnumParticle.WATER_WAKE),
   
   /**
    * A particle effect which is displayed by water:
    *
    * * It looks like a tiny blue square
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   SUSPENDED(EnumParticle.SUSPENDED),
   
   /**
    * A particle effect which is displayed by air when close to bedrock and the in the void:
    *
    * * It looks like a tiny gray square
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   SUSPENDED_DEPTH(EnumParticle.SUSPENDED_DEPTH),
   
   /**
    * A particle effect which is displayed when landing a critical hit and by arrows:
    *
    * * It looks like a light brown cross
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   CRIT(EnumParticle.CRIT),
   
   /**
    * A particle effect which is displayed when landing a hit with an enchanted weapon:
    *
    * * It looks like a cyan star
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   CRIT_MAGIC(EnumParticle.CRIT_MAGIC),
   
   /**
    * A particle effect which is displayed by primed tnt,
    * torches, droppers, dispensers, end portals, brewing stands and monster spawners:
    *
    * * It looks like a little gray cloud
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   SMOKE_NORMAL(EnumParticle.SMOKE_NORMAL),
   
   /**
    * A particle effect which is displayed by fire, minecarts with furnace and blazes:
    *
    * * It looks like a large gray cloud
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   SMOKE_LARGE(EnumParticle.SMOKE_LARGE),
   
   /**
    * A particle effect which is displayed when splash potions or bottles o' enchanting hit something:
    *
    * * It looks like a white swirl
    * * The speed value causes the particle to only move upwards when set to 0
    * * Argument count: 0
    */
   SPELL(EnumParticle.SPELL),
   
   /**
    * A particle effect which is displayed when instant splash potions hit something:
    *
    * * It looks like a white cross
    * * The speed value causes the particle to only move upwards when set to 0
    * * Argument count: 0
    */
   SPELL_INSTANT(EnumParticle.SPELL_INSTANT),
   
   /**
    * A particle effect which is displayed by entities with active potion effects:
    *
    * * It looks like a colored swirl
    * * The speed value causes the particle to be colored black when set to 0
    * * The particle color gets lighter when increasing the speed and darker when decreasing the speed
    * * Argument count: 0
    */
   SPELL_MOB(EnumParticle.SPELL_MOB),
   
   /**
    * A particle effect which is displayed by entities with active potion effects applied through a beacon:
    *
    * * It looks like a transparent colored swirl
    * * The speed value causes the particle to be always colored black when set to 0
    * * The particle color gets lighter when increasing the speed and darker when decreasing the speed
    * * Argument count: 0
    */
   SPELL_MOB_AMBIENT(EnumParticle.SPELL_MOB_AMBIENT),
   
   /**
    * A particle effect which is displayed by witches:
    *
    * * It looks like a purple cross
    * * The speed value causes the particle to only move upwards when set to 0
    * * Argument count: 0
    */
   SPELL_WITCH(EnumParticle.SPELL_WITCH),
   
   /**
    * A particle effect which is displayed by blocks beneath a water source:
    *
    * * It looks like a blue drip
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   DRIP_WATER(EnumParticle.DRIP_WATER),
   
   /**
    * A particle effect which is displayed by blocks beneath a lava source:
    *
    * * It looks like an orange drip
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   DRIP_LAVA(EnumParticle.DRIP_LAVA),
   
   /**
    * A particle effect which is displayed when attacking a villager in a village:
    *
    * * It looks like a cracked gray heart
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   VILLAGER_ANGRY(EnumParticle.VILLAGER_ANGRY),
   
   /**
    * A particle effect which is displayed when using bone meal and trading with a villager in a village:
    *
    * * It looks like a green star
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   VILLAGER_HAPPY(EnumParticle.VILLAGER_HAPPY),
   
   /**
    * A particle effect which is displayed by mycelium:
    *
    * * It looks like a tiny gray square
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   TOWN_AURA(EnumParticle.TOWN_AURA),
   
   /**
    * A particle effect which is displayed by note blocks:
    *
    * * It looks like a colored note
    * * The speed value causes the particle to be colored green when set to 0
    * * Argument count: 0
    */
   NOTE(EnumParticle.NOTE),
   
   /**
    * A particle effect which is displayed by nether portals, endermen,
    * ender pearls, eyes of ender, ender chests and dragon eggs:
    *
    * * It looks like a purple cloud
    * * The speed value influences the spread of this particle effect
    * * Argument count: 0
    */
   PORTAL(EnumParticle.PORTAL),
   
   /**
    * A particle effect which is displayed by enchantment tables which are nearby bookshelves:
    *
    * * It looks like a cryptic white letter
    * * The speed value influences the spread of this particle effect
    * * Argument count: 0
    */
   ENCHANTMENT_TABLE(EnumParticle.ENCHANTMENT_TABLE),
   
   /**
    * A particle effect which is displayed by torches, active furnaces, magma cubes and monster spawners:
    *
    * * It looks like a tiny flame
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   FLAME(EnumParticle.FLAME),
   
   /**
    * A particle effect which is displayed by lava:
    *
    * * It looks like a spark
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   LAVA(EnumParticle.LAVA),
   
   /**
    * A particle effect which is currently unused:
    *
    * * It looks like a transparent gray square
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   FOOTSTEP(EnumParticle.FOOTSTEP),
   
   /**
    * A particle effect which is displayed when a mob dies:
    *
    * * It looks like a large white cloud
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   CLOUD(EnumParticle.CLOUD),
   
   /**
    * A particle effect which is displayed by redstone ore,
    * powered redstone, redstone torches and redstone repeaters:
    *
    * * It looks like a tiny colored cloud
    * * The speed value causes the particle to be colored red when set to 0
    * * Argument count: 0
    */
   REDSTONE(EnumParticle.REDSTONE),
   
   /**
    * A particle effect which is displayed when snowballs hit a block:
    *
    * * It looks like a little piece with the snowball texture
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   SNOWBALL(EnumParticle.SNOWBALL),
   
   /**
    * A particle effect which is currently unused:
    *
    * * It looks like a tiny white cloud
    * * The speed value influences the velocity at which the particle flies off
    * * Argument count: 0
    */
   SNOW_SHOVEL(EnumParticle.SNOW_SHOVEL),
   
   /**
    * A particle effect which is displayed by slimes:
    *
    * * It looks like a tiny part of the slimeball icon
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   SLIME(EnumParticle.SLIME),
   
   /**
    * A particle effect which is displayed when breeding and taming animals:
    *
    * * It looks like a red heart
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   HEART(EnumParticle.HEART),
   
   /**
    * A particle effect which is displayed by barriers:
    *
    * * It looks like a red box with a slash through it
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   BARRIER(EnumParticle.BARRIER),
   
   /**
    * A particle effect which is displayed when breaking a tool or eggs hit a block:
    *
    * * It looks like a little piece with an item texture
    * * Must have a material setted for this particle, otherwise will occur a crash.
    * * Argument count: 2
    */
   ITEM_CRACK(EnumParticle.ITEM_CRACK),
   
   /**
    * A particle effect which is displayed when breaking blocks or sprinting:
    *
    * * It looks like a little piece with a block texture
    * * The speed value has no influence on this particle effect
    * * Must have a material setted for this particle, otherwise will occur a crash.
    * * Argument count: 1
    */
   BLOCK_CRACK(EnumParticle.BLOCK_CRACK),
   
   /**
    * A particle effect which is displayed when falling:
    *
    * * It looks like a little piece with a block texture
    * * Must have a material setted for this particle, otherwise will occur a crash.
    * * Argument count: 1
    */
   BLOCK_DUST(EnumParticle.BLOCK_DUST),
   
   /**
    * A particle effect which is displayed when rain hits the ground:
    *
    * * It looks like a blue droplet
    * * The speed value has no influence on this particle effect
    * * Argument count: 0
    */
   WATER_DROP(EnumParticle.WATER_DROP),
   
   /**
    * A particle effect which is currently unused:
    *
    * * It has no visual effect
    * * Must have a material setted for this particle, otherwise will occur a crash.
    * * Argument count: 0
    */
   ITEM_TAKE(EnumParticle.ITEM_TAKE),
   
   /**
    * A particle effect which is displayed by elder guardians:
    *
    * * It looks like the shape of the elder guardian
    * * The speed value has no influence on this particle effect
    * * The location value has no influence on this particle effect.
    * * Argument count: 0
    */
   MOB_APPEARANCE(EnumParticle.MOB_APPEARANCE);
   
   /**
    * Returns the namespace of this particle.
    */
   val namespace: String = type.b()
   
   /**
    * Returns the id of this particle.
    */
   val id: Int = type.c()
   
   /**
    * Verify if this particle ignores range.
    */
   val ignoreRange: Boolean = type.e()
   
   /**
    * Returns the argument amount of this particle.
    *
    * This is the amount of arguments/options that's this particle accepts when sending a packet.
    */
   val argumentAmount: Int = type.d()
   
   /**
    * Verify if this particle can have arguments.
    */
   val hasArgument: Boolean
      get() = argumentAmount > 0
   
   /**
    * Returns if this particle can have colors.
    */
   val hasColor: Boolean
      get() = id == 15 || id == 16 || id == 30
   
   /**
    * Returns if this particle must have a specified material data to be able to use.
    */
   val mustHaveMaterial: Boolean
      get() = id == 36 || id == 37 || id == 38 || id == 40
   
   /**
    * Creates a packet used to display this particle.
    */
   fun packet(
      location: Location,
      amount: Int = 1,
      speed: Float = 0f,
      offsetX: Float = 0f,
      offsetY: Float = 0f,
      offsetZ: Float = 0f,
      vararg options: Int,
   ): ParticlePacket = ParticlePacket(
      type,
      ignoreRange,
      location.x.toFloat(),
      location.y.toFloat(),
      location.z.toFloat(),
      offsetX,
      offsetY,
      offsetZ,
      speed,
      amount,
      *options
   )
   
   /**
    * Plays this particle to only specified player in the specified location.
    */
   fun play(
      player: Player,
      location: Location,
      amount: Int = 1,
      speed: Float = 0f,
      range: Double = 2048.0,
      offsetX: Float = 0f,
      offsetY: Float = 0f,
      offsetZ: Float = 0f,
      vararg options: Int,
   ) {
      if (!player.isNear(location, range))
         return
      
      player.sendPacket(packet(location, amount, speed, offsetX, offsetY, offsetZ, *options))
   }
   
   /**
    * Plays this particle to all players of the location world in the specified location.
    */
   fun play(
      location: Location,
      amount: Int = 1,
      speed: Float = 0f,
      range: Double = 2048.0,
      offsetX: Float = 0f,
      offsetY: Float = 0f,
      offsetZ: Float = 0f,
      vararg options: Int,
   ) {
      val packet = packet(location, amount, speed, offsetX, offsetY, offsetZ, *options)
      for (player in location.world.players) {
         if (!player.isNear(location, range))
            continue
         
         player.sendPacket(packet)
      }
   }
   
   /**
    * Plays this particle to all players in the specified location world
    * filtered by the given [predicate].
    */
   inline fun play(
      location: Location,
      amount: Int = 1,
      speed: Float = 0f,
      range: Double = 2048.0,
      offsetX: Float = 0f,
      offsetY: Float = 0f,
      offsetZ: Float = 0f,
      vararg options: Int,
      predicate: (Player) -> Boolean,
   ) {
      val packet = packet(location, amount, speed, offsetX, offsetY, offsetZ, *options)
      for (player in location.world.players) {
         if (!player.isNear(location, range))
            continue
         
         if (predicate(player))
            player.sendPacket(packet)
      }
   }
   
   companion object : Set<Particle> by EnumSet.allOf(Particle::class.java) {
      
      fun playColoured(particle: Particle, player: Player, location: Location, color: Color, range: Double = 2048.0) {
         if (!particle.hasColor) return
         particle.play(player, location, 0, 1f, range, color.red / 255f, color.green / 255f, color.blue / 255f)
      }
      
      fun playColoured(particle: Particle, location: Location, color: Color, range: Double = 2048.0) {
         if (!particle.hasColor) return
         particle.play(location, 0, 1f, range, color.red / 255f, color.green / 255f, color.blue / 255f)
      }
      
      inline fun playColoured(
         particle: Particle,
         location: Location,
         color: Color,
         range: Double = 2048.0,
         predicate: (Player) -> Boolean,
      ) {
         if (!particle.hasColor) return
         particle.play(
            location, 0, 1f, range, color.red / 255f, color.green / 255f, color.blue / 255f, predicate = predicate
         )
      }
      
      fun playColoured(
         particle: Particle,
         player: Player,
         location: Location,
         red: Int,
         green: Int,
         blue: Int,
         range: Double = 2048.0,
      ) {
         if (!particle.hasColor) return
         particle.play(player, location, 0, 1f, range, red / 255f, green / 255f, blue / 255f)
      }
      
      fun playColoured(
         particle: Particle, location: Location, red: Int, green: Int, blue: Int, range: Double = 2048.0,
      ) {
         if (!particle.hasColor) return
         particle.play(location, 0, 1f, range, red / 255f, green / 255f, blue / 255f)
      }
      
      inline fun playColoured(
         particle: Particle,
         location: Location,
         red: Int,
         green: Int,
         blue: Int,
         range: Double = 2048.0,
         predicate: (Player) -> Boolean,
      ) {
         if (!particle.hasColor) return
         particle.play(location, 0, 1f, range, red / 255f, green / 255f, blue / 255f, predicate = predicate)
      }
      
      fun playRedstone(player: Player, location: Location, color: Color, range: Double = 2048.0) {
         playColoured(REDSTONE, player, location, color, range)
      }
      
      fun playRedstone(location: Location, color: Color, range: Double = 2048.0) {
         playColoured(REDSTONE, location, color, range)
      }
      
      inline fun playRedstone(
         location: Location, color: Color, range: Double = 2048.0, predicate: (Player) -> Boolean,
      ) {
         playColoured(REDSTONE, location, color, range, predicate)
      }
      
      fun playRedstone(player: Player, location: Location, red: Int, green: Int, blue: Int, range: Double = 2048.0) {
         playColoured(REDSTONE, player, location, red, green, blue, range)
      }
      
      fun playRedstone(location: Location, red: Int, green: Int, blue: Int, range: Double = 2048.0) {
         playColoured(REDSTONE, location, red, green, blue, range)
      }
      
      inline fun playRedstone(
         location: Location,
         red: Int,
         green: Int,
         blue: Int,
         range: Double = 2048.0,
         predicate: (Player) -> Boolean,
      ) {
         playColoured(REDSTONE, location, red, green, blue, range, predicate)
      }
      
      fun playItemBreak(
         player: Player,
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
      ) = ITEM_CRACK.play(
         player,
         location,
         amount,
         speed,
         range,
         offsetX,
         offsetY,
         offsetZ,
         material.subdata shl 12 or (material.id and 0xFFF)
      )
      
      fun playItemBreak(
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
      ) = ITEM_CRACK.play(
         location, amount, speed, range, offsetX, offsetY, offsetZ, material.subdata shl 12 or (material.id and 0xFFF)
      )
      
      inline fun playItemBreak(
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
         predicate: (Player) -> Boolean,
      ) = ITEM_CRACK.play(
         location,
         amount,
         speed,
         range,
         offsetX,
         offsetY,
         offsetZ,
         material.subdata shl 12 or (material.id and 0xFFF),
         predicate = predicate
      )
      
      fun playBlockBreak(
         player: Player,
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
      ) = BLOCK_CRACK.play(
         player,
         location,
         amount,
         speed,
         range,
         offsetX,
         offsetY,
         offsetZ,
         material.subdata shl 12 or (material.id and 0xFFF)
      )
      
      fun playBlockBreak(
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
      ) = BLOCK_CRACK.play(
         location, amount, speed, range, offsetX, offsetY, offsetZ, material.subdata shl 12 or (material.id and 0xFFF)
      )
      
      inline fun playBlockBreak(
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
         predicate: (Player) -> Boolean,
      ) = BLOCK_CRACK.play(
         location,
         amount,
         speed,
         range,
         offsetX,
         offsetY,
         offsetZ,
         material.subdata shl 12 or (material.id and 0xFFF),
         predicate = predicate
      )
      
      fun playBlockDust(
         player: Player,
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
      ) = BLOCK_DUST.play(
         player,
         location,
         amount,
         speed,
         range,
         offsetX,
         offsetY,
         offsetZ,
         material.subdata shl 12 or (material.id and 0xFFF)
      )
      
      fun playBlockDust(
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
      ) = BLOCK_DUST.play(
         location, amount, speed, range, offsetX, offsetY, offsetZ, material.subdata shl 12 or (material.id and 0xFFF)
      )
      
      inline fun playBlockDust(
         location: Location,
         material: Materials,
         amount: Int = 1,
         speed: Float = 0f,
         range: Double = 2048.0,
         offsetX: Float = 0f,
         offsetY: Float = 0f,
         offsetZ: Float = 0f,
         predicate: (Player) -> Boolean,
      ) = BLOCK_DUST.play(
         location,
         amount,
         speed,
         range,
         offsetX,
         offsetY,
         offsetZ,
         material.subdata shl 12 or (material.id and 0xFFF),
         predicate = predicate
      )
   }
}

/**
 * Represents a data holder for particle.
 *
 * Can be used to store predefined data, such, amount,
 * speed and others to easily displayer.
 */
open class ParticleData(
   var particle: Particle,
   var offsetX: Float,
   var offsetY: Float,
   var offsetZ: Float,
   var speed: Float,
   var amount: Int,
   var ignoreRange: Boolean,
   var range: Double,
) {
   
   /**
    * Returns the particle packet used to send to players.
    */
   open fun packet(location: Location): ParticlePacket {
      return particle.packet(location, amount, speed, offsetX, offsetY, offsetZ)
   }
   
   /**
    * Plays this particle data for all players in the world of [location].
    */
   open fun play(location: Location) {
      val packet = packet(location)
      for (player in location.world.players) {
         if (!ignoreRange && !player.isNear(location, range))
            continue
         
         player.sendPacket(packet)
      }
   }
   
   /**
    * Plays this particle data for all players in the world of [location].
    */
   open fun play(player: Player, location: Location) {
      if (!player.isNear(location, range))
         return
      
      player.sendPacket(packet(location))
   }
   
   /**
    * Plays this particle data for all players in the world of [location].
    */
   open fun play(location: Location, predicate: (Player) -> Boolean) {
      val packet = packet(location)
      for (player in location.world.players) {
         if (!ignoreRange && !player.isNear(location, range))
            continue
         
         if (predicate(player))
            player.sendPacket(packet)
      }
   }
}

/**
 * Represents a data holder for particle that's requires a material.
 *
 * Can be used to store predefined data, such, amount,
 * speed and others to easily displayer.
 */
open class MaterialParticle(
   particle: Particle,
   var material: Materials,
   offsetX: Float,
   offsetY: Float,
   offsetZ: Float,
   speed: Float,
   amount: Int,
   ignoreRange: Boolean,
   range: Double,
) : ParticleData(particle, offsetX, offsetY, offsetZ, speed, amount, ignoreRange, range) {
   override fun packet(location: Location): ParticlePacket {
      return particle.packet(
         location,
         amount,
         speed,
         offsetX,
         offsetY,
         offsetZ,
         material.subdata shl 12 or (material.id and 0xFFF)
      )
   }
}

/**
 * Represents a data holder for particle that's requires a material.
 *
 * Can be used to store predefined data, such, amount,
 * speed and others to easily displayer.
 */
open class ColouredParticle(
   particle: Particle,
   var red: Int,
   var green: Int,
   var blue: Int,
   ignoreRange: Boolean,
   range: Double,
) : ParticleData(particle, red.toFloat(), green.toFloat(), blue.toFloat(), 1f, 0, ignoreRange, range) {
   override fun packet(location: Location): ParticlePacket {
      return particle.packet(location, amount, 1f, red / 255f, green / 255f, blue / 255f, 0)
   }
}

/**
 * Creates a particle data with the given details.
 */
fun particle(
   particle: Particle,
   offsetX: Float = 0f,
   offsetY: Float = 0f,
   offsetZ: Float = 0f,
   speed: Float = 0f,
   amount: Int = 1,
   ignoreRange: Boolean = false,
   range: Double = 2048.0,
) = ParticleData(particle, offsetX, offsetY, offsetZ, speed, amount, ignoreRange, range)

/**
 * Creates a material particle data with the given details.
 */
fun particle(
   particle: Particle,
   material: Materials,
   offsetX: Float = 0f,
   offsetY: Float = 0f,
   offsetZ: Float = 0f,
   speed: Float = 0f,
   amount: Int = 1,
   ignoreRange: Boolean = false,
   range: Double = 2048.0,
) = MaterialParticle(particle, material, offsetX, offsetY, offsetZ, speed, amount, ignoreRange, range)

/**
 * Creates a coloured particle data with the given details.
 */
fun particle(
   particle: Particle,
   red: Int,
   green: Int,
   blue: Int,
   ignoreRange: Boolean = false,
   range: Double = 2048.0,
) = ColouredParticle(particle, red, green, blue, ignoreRange, range)

/**
 * Creates a coloured particle data with the given details.
 */
fun particle(
   particle: Particle,
   color: Color,
   ignoreRange: Boolean = false,
   range: Double = 2048.0,
) = ColouredParticle(particle, color.red, color.green, color.blue, ignoreRange, range)
