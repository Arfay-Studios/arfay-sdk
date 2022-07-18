package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import it.unimi.dsi.fastutil.bytes.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [ByteArray].
 */
@Serializable(ByteArrayTagSerializer::class)
open class ByteArrayTag : ByteArrayList, ArrayTag {
   
   override val type
      get() = Type
   
   val value: ByteArray
      get() = a
   
   constructor() : super()
   constructor(size: Int) : super(size)
   constructor(value: ByteArray) : super(value)
   
   override fun write(data: DataOutput) {
      data.writeInt(size)
      data.write(elements())
   }
   
   override fun elementType() = ByteType
   override fun copy() = ByteArrayTag(value)
   override fun toString() = "[${size} bytes]"
   
   /**
    * Type tag of [ByteArrayTag].
    */
   object Type : TagType<ByteArrayTag> {
      override val id: Byte get() = 7
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): ByteArrayTag {
         val array = ByteArray(data.readInt())
         data.readFully(array)
         return ByteArrayTag(array)
      }
   }
}
