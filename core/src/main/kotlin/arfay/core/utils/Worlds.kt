package arfay.core.utils

import net.minecraft.server.v1_8_R3.WorldServer
import org.bukkit.*
import org.bukkit.block.*
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld
import org.bukkit.entity.*
import org.bukkit.material.*

/**
 * Gets this world as [CraftWorld].
 */
val World.craftWorld: CraftWorld
   get() = this as CraftWorld

/**
 * Gets the NMS handler of this world.
 */
val World.handler: WorldServer
   get() = craftWorld.handler


fun mainWorld(): World = Bukkit.getWorlds()[0]
fun chunk(world: World, x: Int, y: Int): Chunk = world.getChunkAt(x, y)
fun chunk(block: Block) = chunk(block.world, block.x shr 4, block.z shr 4)

inline fun <reified T : Entity> World.spawn(location: Location): T {
   return spawn(location, T::class.java)
}

inline fun <reified T : Entity> World.getEntitiesByClass(): Collection<T> {
   return getEntitiesByClass(T::class.java)
}

fun World.dropItem(location: Location, material: Material, data: Byte = 0): Item {
   return dropItem(location, material.asMaterialData(data))
}

fun World.dropItem(location: Location, materialData: MaterialData): Item {
   return dropItem(location, materialData.toItemStack(1))
}

fun World.dropItemNaturally(location: Location, material: Material, data: Byte = 0): Item {
   return dropItemNaturally(location, material.asMaterialData(data))
}

fun World.dropItemNaturally(location: Location, materialData: MaterialData): Item {
   return dropItemNaturally(location, materialData.toItemStack(1))
}

fun World.setSpawnLocation(location: Location): Boolean {
   return setSpawnLocation(location.blockX, location.blockY, location.blockZ)
}

fun World.setSpawnLocation(block: Block): Boolean {
   return setSpawnLocation(block.x, block.y, block.z)
}
