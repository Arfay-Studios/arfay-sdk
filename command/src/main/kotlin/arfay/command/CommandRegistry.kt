package arfay.command

import arfay.core.utils.*
import org.bukkit.command.*
import org.bukkit.craftbukkit.v1_8_R3.*

/**
 * Represents a manager for instructors.
 */
object CommandRegistry : HashMap<String, Instructor>() {
   
   /**
    * Register a instructor in the server.
    */
   fun register(instructor: Instructor) {
      put(instructor.name, instructor)
      commandMap.register(instructor.label, instructor)
   }
   
   /**
    * Unregister a instructor in the server.
    */
   fun unregister(name: String) {
      remove(name)
      knowCommands.remove(name)
   }
   
   val commandMap: SimpleCommandMap by lazy {
      CraftServer::class.java.getDeclaredField("commandMap").apply {
         isAccessible = true
      }.get(server) as SimpleCommandMap
   }
   
   val knowCommands: MutableMap<String, Command> by lazy {
      SimpleCommandMap::class.java.getDeclaredField("knownCommands").apply {
         isAccessible = true
      }.get(commandMap) as MutableMap<String, Command>
   }
}
