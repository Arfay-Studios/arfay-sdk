package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an empty tag.
 *
 * Empty tags don't write/read anything and is just for informational purposes.
 */
@Serializable(EmptyTagSerializer::class)
object EmptyTag : Tag {
   
   override val type
      get() = Type
   
   override fun write(data: DataOutput) = Unit
   override fun copy() = EmptyTag
   override fun toString() = "EmptyTag"
   
   /**
    * Type tag of [EmptyTag].
    */
   object Type : TagType<EmptyTag> {
      override val id: Byte get() = 0
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): EmptyTag {
         return EmptyTag
      }
   }
}
