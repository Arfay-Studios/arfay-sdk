package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import com.soywiz.kds.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [Int].
 */
@Serializable(IntTagSerializer::class)
data class IntTag internal constructor(var value: Int = 0) : NumberTag, Comparable<IntTag> {
   
   override val type
      get() = Type
   
   override fun write(data: DataOutput) {
      data.writeInt(value)
   }
   
   override fun toNumber(): Number = value
   override fun toByte(): Byte = value.toByte()
   override fun toShort(): Short = value.toShort()
   override fun toInt(): Int = value
   override fun toLong(): Long = value.toLong()
   override fun toFloat(): Float = value.toFloat()
   override fun toDouble(): Double = value.toDouble()
   
   override fun copy() = this
   override fun toString() = value.toString()
   override fun equals(other: Any?) = other is IntTag && value == other.value
   override fun hashCode() = hashCode(value)
   override fun compareTo(other: IntTag) = value.compareTo(other.value)
   
   /**
    * Type tag of [IntTag].
    */
   object Type : TagType<IntTag> {
      override val id: Byte get() = 3
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): IntTag {
         return of(data.readInt())
      }
   }
   
   companion object {
      private val CACHE = Array(1153) {
         IntTag(it - 128)
      }
      
      fun of(value: Int): IntTag {
         return if (value in -128..1024) CACHE[value - -128] else IntTag(value)
      }
   }
}

