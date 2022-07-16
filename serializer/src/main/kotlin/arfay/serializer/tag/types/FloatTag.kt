package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import com.soywiz.kds.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [Float].
 */
@Serializable(FloatTagSerializer::class)
data class FloatTag internal constructor(var value: Float = 0f) : NumberTag, Comparable<FloatTag> {
  
   override val type
      get() = Type
   
   override fun write(data: DataOutput) {
      data.writeFloat(value)
   }
   
   override fun toNumber(): Number = value
   override fun toByte(): Byte = toInt().toByte()
   override fun toShort(): Short = toInt().toShort()
   override fun toInt(): Int = value.toInt()
   override fun toLong(): Long = value.toLong()
   override fun toFloat(): Float = value
   override fun toDouble(): Double = value.toDouble()
   
   override fun copy() = this
   override fun toString() = "${value}f"
   override fun equals(other: Any?) = other is FloatTag && value == other.value
   override fun hashCode() = hashCode(value)
   override fun compareTo(other: FloatTag) = value.compareTo(other.value)
   
   /**
    * Type tag of [FloatTag].
    */
   object Type : TagType<FloatTag> {
      override val id: Byte get() = 5
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): FloatTag {
         return of(data.readFloat())
      }
   }
   
   companion object {
      @JvmField val ZERO = FloatTag()
      
      fun of(value: Float): FloatTag {
         return if (value == 0f) ZERO else FloatTag(value)
      }
   }
}

