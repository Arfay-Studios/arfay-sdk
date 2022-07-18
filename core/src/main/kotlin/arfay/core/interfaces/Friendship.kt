package arfay.core.interfaces

import arfay.core.utils.uuid
import org.bukkit.entity.*
import java.util.*

/**
 * A friendship interface, representing an object that can have any friends.
 */
interface Friendship {
   
   /**
    * The friends of this friendship.
    */
   var friends: MutableSet<UUID>
   
   /**
    * Verifies if the player id is accessible in any part.
    *
    * By default, this just verifies if the player is friends of this friendship object.
    *
    * If you also implement the [Ownerable] interface, you must override this function.
    */
   fun isAccessible(id: UUID): Boolean = id in friends
   
   /**
    * Verifies if the player is accessible in any part.
    */
   fun isAccessible(player: Player): Boolean = isAccessible(player.uuid)
   
   /**
    * Returns if this id of player is friend of this friendship object.
    */
   fun isFriend(id: UUID): Boolean = id in friends
   
   /**
    * Returns if this player is friend of this friendship object.
    */
   fun isFriend(player: Player): Boolean = player.uuid in friends
   
   /**
    * Adds a new specified friend in this friendship object.
    */
   fun addFriend(id: UUID) = friends.add(id)
   
   /**
    * Adds a new specified friend in this friendship object.
    */
   fun addFriend(player: Player) = friends.add(player.uuid)
   
   /**
    * Removes a specified friend in this friendship object.
    */
   fun removeFriend(id: UUID) = friends.remove(id)
   
   /**
    * Removes a specified friend in this friendship object.
    */
   fun removeFriend(player: Player) = friends.remove(player.uuid)
}
