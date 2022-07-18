package arfay.core.interfaces

import arfay.core.utils.toOfflinePlayer
import arfay.core.utils.toPlayer
import arfay.core.utils.uuid
import org.bukkit.*
import org.bukkit.entity.*
import java.util.*

/**
 * An ownerable interface, representing an object that can have an owner.
 */
interface Ownerable {
   
   /**
    * The owner identifier of this ownerable object.
    */
   var ownerId: UUID
   
   /**
    * Returns the owner name of this ownerable object.
    */
   val ownerName: String
      get() = offlineOwner.name
   
   /**
    * Returns the owner as player of this ownerable object.
    */
   val owner: Player
      get() = ownerId.toPlayer()
   
   /**
    * Returns the owner as offline player of this ownerable object.
    */
   val offlineOwner: OfflinePlayer
      get() = ownerId.toOfflinePlayer()
   
   /**
    * Returns if the specified player is owner of this ownerable object instance.
    */
   fun isOwner(player: OfflinePlayer): Boolean = ownerId == player.uuid
   
   /**
    * Returns if the specified player is owner of this ownerable object instance.
    */
   fun isOwner(player: Player): Boolean = ownerId == player.uuid
   
   /**
    * Returns if the specified UUID is owner of this ownerable object instance.
    */
   fun isOwner(id: UUID): Boolean = ownerId == id
}
