package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import it.unimi.dsi.fastutil.ints.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [IntArrayTag].
 */
@Serializable(IntArrayTagSerializer::class)
open class IntArrayTag : IntArrayList, ArrayTag {
   
   override val type
      get() = Type
   
   val value: IntArray
      get() = a
   
   constructor() : super()
   constructor(size: Int) : super(size)
   constructor(value: IntArray) : super(value)
   
   override fun write(data: DataOutput) {
      data.writeInt(size)
      
      // use unboxed int values
      with(intIterator()) {
         while (hasNext()) data.writeInt(nextInt())
      }
   }
   
   override fun elementType() = IntType
   override fun copy() = IntArrayTag(value)
   
   override fun toString(): String = buildString {
      append('[')
      this@IntArrayTag.forEach {
         append("$it,")
      }
      append(']')
   }
   
   /**
    * Type tag of [IntArrayTag].
    */
   object Type : TagType<IntArrayTag> {
      override val id: Byte get() = 11
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): IntArrayTag {
         val size = data.readInt()
         val array = IntArray(size) {
            data.readInt()
         }
         
         return IntArrayTag(array)
      }
   }
}
