package arfay.serializer.tag.types

import arfay.serializer.serial.*
import arfay.serializer.tag.*
import com.soywiz.kds.*
import kotlinx.serialization.Serializable
import java.io.*

/**
 * Represents an NBT tag of type [Short].
 */
@Serializable(ShortTagSerializer::class)
data class ShortTag internal constructor(var value: Short = 0) : NumberTag, Comparable<ShortTag> {
   
   override val type
      get() = Type
   
   override fun write(data: DataOutput) {
      data.writeShort(toInt())
   }
   
   override fun toNumber(): Number = value
   override fun toByte(): Byte = value.toByte()
   override fun toShort(): Short = value
   override fun toInt(): Int = value.toInt()
   override fun toLong(): Long = value.toLong()
   override fun toFloat(): Float = value.toFloat()
   override fun toDouble(): Double = value.toDouble()
   
   override fun copy() = this
   override fun toString() = "${value}s"
   override fun equals(other: Any?) = other is ShortTag && value == other.value
   override fun hashCode() = hashCode(value)
   override fun compareTo(other: ShortTag) = value.compareTo(other.value)
   
   /**
    * Type tag of [ShortTag].
    */
   object Type : TagType<ShortTag> {
      override val id: Byte get() = 2
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): ShortTag {
         return of(data.readShort())
      }
   }
   
   companion object {
      private val CACHE = Array(1153) {
         ShortTag((it - 128).toShort())
      }
      
      fun of(value: Short): ShortTag {
         return if (value in -128..1024) CACHE[value - -128] else ShortTag(value)
      }
   }
}

