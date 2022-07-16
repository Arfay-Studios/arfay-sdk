package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import com.soywiz.kds.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [Byte].
 */
@Serializable(ByteTagSerializer::class)
data class ByteTag internal constructor(var value: Byte = 0) : NumberTag, Comparable<ByteTag> {
   
   override val type
      get() = Type
   
   override fun write(data: DataOutput) {
      data.writeByte(toInt())
   }
   
   override fun toNumber(): Number = value
   override fun toByte(): Byte = value
   override fun toShort(): Short = value.toShort()
   override fun toInt(): Int = value.toInt()
   override fun toLong(): Long = value.toLong()
   override fun toFloat(): Float = value.toFloat()
   override fun toDouble(): Double = value.toDouble()
   
   override fun copy() = this
   override fun toString() = "${value}b"
   override fun equals(other: Any?) = other is ByteTag && value == other.value
   override fun hashCode() = hashCode(value)
   override fun compareTo(other: ByteTag) = value.compareTo(other.value)
   
   /**
    * Type tag of [ByteTag].
    */
   object Type : TagType<ByteTag> {
      override val id: Byte get() = 1
      override val supportItems: Boolean get() = true
   
      override fun load(data: DataInput): ByteTag {
         return of(data.readByte())
      }
   }
   
   companion object {
      private val CACHE = Array(256) {
         ByteTag((it - 128).toByte())
      }
      
      @JvmField val ONE = of(1)
      @JvmField val ZERO = of(0)
      
      fun of(value: Byte) = CACHE[value + 128]
      fun of(value: Boolean) = if (value) ONE else ZERO
   }
}
