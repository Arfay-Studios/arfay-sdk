package walkmc.interfaces

import arfay.core.utils.*
import org.bukkit.*
import org.bukkit.block.*
import org.bukkit.entity.*
import walkmc.*

/**
 * A localizable interface, representing an object that have a location.
 */
interface Localizable {
   
   /**
    * The location of this localizable.
    */
   var location: Location
}


/**
 * Returns the nearest entities filtered in the specified radius as list
 */
@JvmName("nearbyEntitiesType")
inline fun <reified T : Entity> Localizable.getNearbyEntitiesOf(
   x: Double, y: Double, z: Double
) = getNearbyEntities(x, y, z).filterIsInstance<T>()

/**
 * Returns the nearest entity filtered in the specified radius
 */
@JvmName("nearestEntityType")
inline fun <reified T : Entity> Localizable.getNearestEntityOf(
   x: Double, y: Double, z: Double
) = getNearbyEntitiesOf<T>(x, y, z).firstOrNull()

/**
 * Returns the distance between the location of this localizable object
 * and the specified [to] location.
 */
infix fun Localizable.distance(to: Location) = location.distance(to)

/**
 * Returns the distance between the location of this localizable object
 * and the specified [to] block.
 */
infix fun Localizable.distance(to: Block) = location.distance(to.location)

/**
 * Returns the distance between the location of this localizable object
 * and the specified [to] entity.
 */
infix fun Localizable.distance(to: Entity) = location.distance(to.location)

fun Localizable.isNear(to: Location, radius: Double) = distance(to) <= radius
fun Localizable.isNear(to: Entity, radius: Double) = distance(to) <= radius
fun Localizable.isNear(to: Block, radius: Double) = distance(to) <= radius

fun Localizable.isNear(to: Location, radius: Float) = distance(to) <= radius
fun Localizable.isNear(to: Entity, radius: Float) = distance(to) <= radius
fun Localizable.isNear(to: Block, radius: Float) = distance(to) <= radius

fun Localizable.isNear(to: Location, radius: Int) = distance(to) <= radius
fun Localizable.isNear(to: Entity, radius: Int) = distance(to) <= radius
fun Localizable.isNear(to: Block, radius: Int) = distance(to) <= radius

/**
 * Returns all nearby entities with this location as central.
 */
fun Localizable.getNearbyEntities(x: Double, y: Double, z: Double) =
   location.world.getNearbyEntities(location, x, y, z).toList()

fun Localizable.getNearbyEntities(x: Float, y: Float, z: Float) =
   getNearbyEntities(x.toDouble(), y.toDouble(), z.toDouble())

fun Localizable.getNearbyEntities(x: Int, y: Int, z: Int) = getNearbyEntities(x.toDouble(), y.toDouble(), z.toDouble())

/**
 * Returns the nearest entity in the specified radius
 */
fun Localizable.getNearestEntity(x: Double, y: Double, z: Double) = getNearbyEntities(x, y, z).firstOrNull()
fun Localizable.getNearestEntity(x: Float, y: Float, z: Float) = getNearbyEntities(x, y, z).firstOrNull()
fun Localizable.getNearestEntity(x: Int, y: Int, z: Int) = getNearbyEntities(x, y, z).firstOrNull()

/**
 * Returns the nearest entities filtered in the specified radius as list
 */
inline fun Localizable.getNearbyEntities(x: Double, y: Double, z: Double, predicate: (Entity) -> Boolean) =
   getNearbyEntities(x, y, z).filter(predicate)

inline fun Localizable.getNearbyEntities(x: Float, y: Float, z: Float, predicate: (Entity) -> Boolean) =
   getNearbyEntities(x, y, z).filter(predicate)

inline fun Localizable.getNearbyEntities(x: Int, y: Int, z: Int, predicate: (Entity) -> Boolean) =
   getNearbyEntities(x, y, z).filter(predicate)

/**
 * Returns the nearest entity filtered in the specified radius
 */
inline fun Localizable.getNearestEntity(x: Double, y: Double, z: Double, predicate: (Entity) -> Boolean) =
   getNearbyEntities(x, y, z).firstOrNull(predicate)

inline fun Localizable.getNearestEntity(x: Float, y: Float, z: Float, predicate: (Entity) -> Boolean) =
   getNearbyEntities(x, y, z).firstOrNull(predicate)

inline fun Localizable.getNearestEntity(x: Int, y: Int, z: Int, predicate: (Entity) -> Boolean) =
   getNearbyEntities(x, y, z).firstOrNull(predicate)

/**
 * Returns the nearest players filtered in the specified radius as list
 */
fun Localizable.getNearbyPlayers(x: Double, y: Double, z: Double) =
   getNearbyEntities(x, y, z).filterIsInstance<Player>()

fun Localizable.getNearbyPlayers(x: Float, y: Float, z: Float) = getNearbyEntities(x, y, z).filterIsInstance<Player>()
fun Localizable.getNearbyPlayers(x: Int, y: Int, z: Int) = getNearbyEntities(x, y, z).filterIsInstance<Player>()

/**
 * Returns the nearest player filtered in the specified radius
 */
fun Localizable.getNearestPlayer(x: Double, y: Double, z: Double) = getNearbyPlayers(x, y, z).firstOrNull()
fun Localizable.getNearestPlayer(x: Float, y: Float, z: Float) = getNearbyPlayers(x, y, z).firstOrNull()
fun Localizable.getNearestPlayer(x: Int, y: Int, z: Int) = getNearbyPlayers(x, y, z).firstOrNull()

/**
 * Returns all nearby blocks centered by the location of this localizable object.
 */
fun Localizable.getNearbyBlocks(x: Int, y: Int, z: Int) = buildList {
   val block = location.block
   for (i in -x until x) {
      for (j in -y until y) {
         for (k in -z until z) add(block.getRelative(i, j, k))
      }
   }
}

/**
 * Returns all filtered nearby blocks centered by the location of this localizable object.
 */
inline fun Localizable.getNearbyBlocks(x: Int, y: Int, z: Int, predicate: (Block) -> Boolean) =
   getNearbyBlocks(x, y, z).filter(predicate)

/**
 * Returns all filtered nearby blocks centered by the location of this localizable object.
 */
fun Localizable.getNearbyBlocks(x: Int, y: Int, z: Int, material: Materials) =
   getNearbyBlocks(x, y, z) { it.material == material }

