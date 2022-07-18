package arfay.serializer.tag

import it.unimi.dsi.fastutil.io.*
import java.io.*

/**
 * Represents a abstract NBT tag.
 */
interface Tag {
   
   /**
    * The type of this NBT.
    */
   val type: TagType<out Tag>
   
   /**
    * Writes this NBT to the given [data] output.
    */
   fun write(data: DataOutput)
   
   /**
    * Makes a copy of this NBT.
    */
   fun copy(): Tag
   
   /**
    * Converts this NBT to a string.
    */
   override fun toString(): String
}

/**
 * Returns if this NBT is an end NBT: [EmptyTag].
 */
inline val Tag.isEnd: Boolean
   get() = type === EmptyType

/**
 * Returns the type id of this NBT.
 */
inline val Tag.id: Int
   get() = type.id.toInt()

/**
 * Converts this NBT compound to byte array data.
 */
fun Tag.toByteArray(): ByteArray {
   val stream = FastByteArrayOutputStream()
   stream.writeTag(this)
   return stream.array
}

/**
 * Decodes this byte array to a tag.
 */
fun ByteArray.decodeTag() = FastByteArrayInputStream(this).readTag()

/**
 * Decodes this byte array to a compound tag.
 */
fun ByteArray.decodeCompound() = FastByteArrayInputStream(this).readCompound()

