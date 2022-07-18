package arfay.core.entity

import arfay.core.utils.*
import net.minecraft.server.v1_8_R3.*
import net.minecraft.server.v1_8_R3.World
import org.bukkit.*
import walkmc.entity.*
import kotlin.collections.set
import kotlin.reflect.full.*
import java.lang.reflect.Field

/**
 * A registry used to register custom entities.
 */
object Entities {
   
   /**
    * Creates a entity instance without spawning it with the given [world].
    */
   inline fun <reified T : Entity> create(
      world: World
   ): T = with(T::class) {
      java.getDeclaredConstructor(World::class.java).newInstance(world) ?:
         error("Cannot create instance for entity $this, because no constructor with ${World::class} has found")
   }
   
   /**
    * Creates a entity instance and sets the entity position without spawning it
    * in the world of the given [location].
    */
   inline fun <reified T : Entity> create(
      location: Location
   ): T = create<T>(location.world.handler).also {
      it.setPosition(location.x, location.y, location.z)
   }
   
   /**
    * Creates a entity instance and sets the entity position and spawns them in the given [location].
    */
   inline fun <reified T : Entity> spawn(
      location: Location,
      force: Boolean = false
   ): T = create<T>(location).also { it.spawnIn(location.world.handler) }
      
   /**
    * Register a new custom entity of the specified class.
    *
    * Note that the class must be annoted with [Mob].
    */
   inline fun <reified T : Entity> register() {
      val klass = T::class
      val mob = klass.findAnnotation<Mob>() ?: error("The entity class $klass is not annoted with ${Mob::class}")
      val name = mob.name.ifEmpty { klass.simpleName ?: klass.toString() }
      val java = klass.java
      NameToClass[name] = java
      ClassToName[java] = name
      ClassToId[java] = mob.type.typeId.toInt()
   }
   
   val NameToClass: MutableMap<String, Class<out Entity>> by lazy {
      EntityTypes::class.java.getDeclaredField("c").apply { isAccessible = true }.get(null).cast()
   }
   
   val ClassToName: MutableMap<Class<out Entity>, String> by lazy {
      EntityTypes::class.java.getDeclaredField("d").apply { isAccessible = true }.get(null).cast()
   }
   
   val ClassToId: MutableMap<Class<out Entity>, Int> by lazy {
      EntityTypes::class.java.getDeclaredField("f").apply { isAccessible = true }.get(null).cast()
   }
}

/**
 * Register a new custom entity of the specified class.
 *
 * Note that the class must be annoted with [Mob].
 */
inline fun <reified T : Entity> registerEntity() = Entities.register<T>()
