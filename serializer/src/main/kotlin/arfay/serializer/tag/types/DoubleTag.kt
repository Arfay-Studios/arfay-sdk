package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import com.soywiz.kds.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [Double].
 */
@Serializable(DoubleTagSerializer::class)
data class DoubleTag internal constructor(var value: Double = 0.0) : NumberTag, Comparable<DoubleTag> {
   
   override val type
      get() = Type
   
   override fun write(data: DataOutput) {
      data.writeDouble(value)
   }
   
   override fun toNumber(): Number = value
   override fun toByte(): Byte = toInt().toByte()
   override fun toShort(): Short = toInt().toShort()
   override fun toInt(): Int = value.toInt()
   override fun toLong(): Long = value.toLong()
   override fun toFloat(): Float = value.toFloat()
   override fun toDouble(): Double = value
   
   override fun copy() = this
   override fun toString() = "${value}D"
   override fun equals(other: Any?) = other is DoubleTag && value == other.value
   override fun hashCode() = hashCode(value)
   override fun compareTo(other: DoubleTag) = value.compareTo(other.value)
   
   /**
    * Type tag of [DoubleTag].
    */
   object Type : TagType<DoubleTag> {
      override val id: Byte get() = 6
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): DoubleTag {
         return of(data.readDouble())
      }
   }
   
   companion object {
      @JvmField val ZERO = DoubleTag()
      
      fun of(value: Double): DoubleTag {
         return if (value == 0.0) ZERO else DoubleTag(value)
      }
   }
}

