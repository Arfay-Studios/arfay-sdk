package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import com.soywiz.kds.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [String].
 */
@Serializable(StringTagSerializer::class)
data class StringTag internal constructor(var value: String = "") : Tag, CharSequence, Comparable<StringTag> {
  
   override val type
      get() = Type
   
   override val length: Int
      get() = value.length
   
   override fun write(data: DataOutput) {
      data.writeUTF(value)
   }
   
   override fun get(index: Int): Char = value[index]
   override fun subSequence(startIndex: Int, endIndex: Int) = value.subSequence(startIndex, endIndex)
   
   override fun copy() = this
   override fun toString() = "\"" + value.replace("\"", "\\\"") + "\""
   override fun equals(other: Any?) = other is StringTag && value == other.value
   override fun hashCode() = hashCode(value)
   override fun compareTo(other: StringTag) = value.compareTo(other.value)
   
   /**
    * Type tag of [StringTag].
    */
   object Type : TagType<StringTag> {
      override val id: Byte get() = 8
      override val supportItems: Boolean get() = true
   
      override fun load(data: DataInput): StringTag {
         return of(data.readUTF())
      }
   }
   
   companion object {
      @JvmField val EMPTY = StringTag()
      
      fun of(value: String): StringTag {
         return if (value.isEmpty()) EMPTY else StringTag(value)
      }
   }
}
