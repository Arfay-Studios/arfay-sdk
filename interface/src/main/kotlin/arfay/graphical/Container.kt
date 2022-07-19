package arfay.graphical

import arfay.core.utils.*
import arfay.graphical.animations.*
import arfay.graphical.common.*
import arfay.graphical.dsl.*
import arfay.graphical.interfaces.*
import arfay.graphical.schema.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.entity.*
import org.bukkit.inventory.*

typealias OpenInvPacket = PacketPlayOutOpenWindow

/**
 * Represents a container for any inventory created with `interface-framework`.
 *
 * The purpose of this class is to provide better tick execution and Spigot compatibility.
 */
open class Container(
   val graphical: IGraphical,
   playerInventory: IInventory,
   inventory: IInventory,
   player: EntityHuman,
) : ContainerChest(playerInventory, inventory, player) {
   
   var ticks = 0
   
   constructor(graphical: IGraphical, player: Player, inventory: Inventory) :
         this(graphical, player.inventory.handler, inventory.handler, player.handler)
   
   /**
    * Opens this container to [player].
    *
    * Since NMS don't have a method to open an already
    * created container we have to make a shadow copy.
    */
   fun open(player: Player) {
      val handler = player.handler
      val id = handler.nextContainerCounter()
      
      // fix not showing items when open
      handler.activeContainer.transferTo(this, player.craftHandler)
      
      if (handler.activeContainer != handler.defaultContainer)
         handler.closeInventory()
      
      handler.playerConnection.sendPacket(OpenInvPacket(id, "minecraft:chest", ChatMessage(graphical.title), graphical.size))
      handler.activeContainer = this
      windowId = id
      addSlotListener(handler)
      checkReachable = false
   }
   
   override fun b() {
      if (ticks++ % graphical.tickDelay != 0) return
      
      graphical.tick()
      
      for (i in 0 until c.size) {
         val item = c[i].item
         var other = b[i]
         
         if (!NMSItem.fastMatches(other, item) || !NMSItem.matches(other, item)) {
            other = item?.cloneItemStack()
            b[i] = other
            for (listener in listeners) listener.a(this, i, other)
         }
      }
   }
}
