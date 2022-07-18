package arfay.core.utils

import net.minecraft.server.v1_8_R3.*
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
import org.bukkit.entity.*
import org.bukkit.entity.Entity
import org.bukkit.util.*

/**
 * Gets this entity as [CraftEntity].
 */
inline val Entity.craftEntity: CraftEntity
   get() = this as CraftEntity

/**
 * Gets the NMS handler of this entity.
 */
val Entity.handler: NMSEntity
   get() = craftEntity.handle

/**
 * Gets the tag of this entity.
 */
var Entity.tag: NBTCompound
   get() {
      val tag = NBTCompound()
      handler.e(tag)
      return tag
   }
   set(value) = handler.f(value)

/**
 * Gets the distance between this entity to [location].
 */
fun Entity.distance(location: Location): Double {
   return this.location.distance(location)
}

/**
 * Gets the distance between this entity to [entity].
 */
fun Entity.distance(entity: Entity): Double {
   return location.distance(entity.location)
}

/**
 * Returns if this entity is near from [location]
 */
fun Entity.isNear(location: Location, radius: Double): Boolean {
   return distance(location) <= radius
}

/**
 * Returns if this entity is near from [entity]
 */
fun Entity.isNear(entity: Entity, radius: Double): Boolean {
   return distance(entity) <= radius
}

/**
 * Applies and changes the velocity of this player by the specified [velo].
 */
inline fun Entity.velocity(velo: Vector = velocity, block: Vector.() -> Unit) {
   velocity = velo.apply(block)
}

/**
 * Transforms this entity root tag by the specified transform.
 */
inline fun Entity.withTag(transform: NBTTagCompound.() -> Unit) =
   handler.f(tag.apply(transform))

/**
 * Returns if this entity is baby or not.
 */
var Entity.isBaby: Boolean
   get() = when (this) {
      is Ageable -> !isAdult
      is Zombie -> isBaby
      else -> false
   }
   set(value) = when (this) {
      is Ageable -> if (value) setBaby() else setAdult()
      is Zombie -> isBaby = value
      else -> Unit
   }

/**
 * Returns if this entity is adult or not.
 */
var Entity.isAdult: Boolean
   get() = !isBaby
   set(value) {
      isBaby = !value
   }
