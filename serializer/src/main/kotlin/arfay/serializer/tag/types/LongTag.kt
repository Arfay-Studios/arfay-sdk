package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import com.soywiz.kds.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [Long].
 */
@Serializable(LongTagSerializer::class)
data class LongTag internal constructor(var value: Long = 0) : NumberTag, Comparable<LongTag> {
   
   override val type
      get() = Type
   
   override fun toNumber(): Number = value
   override fun toByte(): Byte = value.toByte()
   override fun toShort(): Short = value.toShort()
   override fun toInt(): Int = value.toInt()
   override fun toLong(): Long = value
   override fun toFloat(): Float = value.toFloat()
   override fun toDouble(): Double = value.toDouble()
   
   override fun write(data: DataOutput) {
      data.writeLong(value)
   }
   
   override fun copy() = this
   override fun toString() = "${value}L"
   override fun equals(other: Any?) = other is LongTag && value == other.value
   override fun hashCode() = hashCode(value)
   override fun compareTo(other: LongTag) = value.compareTo(other.value)
   
   /**
    * Type tag of [LongTag].
    */
   object Type : TagType<LongTag> {
      override val id: Byte get() = 4
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): LongTag {
         return of(data.readLong())
      }
   }
   
   companion object {
      private val CACHE = Array(1153) {
         LongTag((it - 128).toLong())
      }
      
      fun of(value: Long): LongTag {
         return if (value in -128..1024) CACHE[(value - -128).toInt()] else LongTag(value)
      }
   }
}
