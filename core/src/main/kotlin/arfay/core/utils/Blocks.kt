package arfay.core.utils

import org.bukkit.block.*

/**
 * Gets the material of this block.
 */
var Block.material: Materials
   get() = Materials.from(type, data.toInt())
   set(value) {
      type = value.type
      data = value.subdata.toByte()
   }

/**
 * Gets the up relative block of this block.
 */
fun Block.up(distance: Int = 1): Block = getRelative(0, distance, 0)

/**
 * Gets the down relative block of this block.
 */
fun Block.down(distance: Int = 1): Block = getRelative(0, -distance, 0)

/**
 * Gets the south relative block of this block.
 */
fun Block.south(distance: Int = 1): Block = getRelative(BlockFace.SOUTH, distance)

/**
 * Gets the north relative block of this block.
 */
fun Block.north(distance: Int = 1): Block = getRelative(BlockFace.NORTH, distance)

/**
 * Gets the east relative location of this location.
 */
fun Block.east(distance: Int = 1): Block = getRelative(BlockFace.EAST, distance)

/**
 * Gets the west relative block of this block.
 */
fun Block.west(distance: Int = 1): Block = getRelative(BlockFace.WEST, distance)

/**
 * Gets the south west relative block of this block.
 */
fun Block.southwest(distance: Int = 1): Block = getRelative(BlockFace.SOUTH_WEST, distance)

/**
 * Gets the north west relative block of this block.
 */
fun Block.northwest(distance: Int = 1): Block = getRelative(BlockFace.NORTH_WEST, distance)

/**
 * Gets the south east relative block of this block.
 */
fun Block.southeast(distance: Int = 1): Block = getRelative(BlockFace.SOUTH_EAST, distance)

/**
 * Gets the north east relative block of this block.
 */
fun Block.northeast(distance: Int = 1): Block = getRelative(BlockFace.NORTH_EAST, distance)
