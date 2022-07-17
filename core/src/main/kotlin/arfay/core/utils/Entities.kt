package arfay.core.utils

import org.bukkit.Location
import org.bukkit.entity.Entity

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
