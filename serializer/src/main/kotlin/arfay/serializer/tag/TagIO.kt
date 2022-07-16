@file:Suppress("NOTHING_TO_INLINE")

package arfay.serializer.tag

import arfay.serializer.tag.types.*
import dream.nbt.*
import it.unimi.dsi.fastutil.io.*
import java.io.*
import java.util.zip.*

/**
 * IO Utility for writing and reading NBT from file/streams.
 */
object TagIO {
   
   /**
    * Reads a NBT in given [input].
    *
    * @param close optionally closes [input].
    */
   fun read(input: InputStream, close: Boolean = true): Tag {
      val stream = input.toNBTStream()
      val type = TagRegistry[stream.readByte()]
      val tag = type.load(stream)
      if (close) stream.close()
      return tag
   }
   
   /**
    * Reads a NBT in given [file].
    *
    * @param close optionally closes [file] stream.
    */
   fun read(file: File, close: Boolean = true): Tag = read(file.inputStream(), close)
   
   /**
    * Reads a [CompoundTag] in given [file].
    *
    * @param close optionally closes [file] stream.
    */
   fun readCompound(file: File, close: Boolean = true): CompoundTag = read(file, close) as CompoundTag
   
   /**
    * Reads a [CompoundTag] in given [input].
    *
    * @param close optionally closes [input].
    */
   fun readCompound(input: InputStream, close: Boolean = true): CompoundTag = read(input, close) as CompoundTag
   
   /**
    * Writes [value] in [output].
    *
    * @param close optionally closes [output].
    */
   fun write(output: OutputStream, value: Tag, close: Boolean = true) {
      val stream = output.toNBTStream()
      stream.writeByte(value.id)
      value.write(stream)
      if (close) stream.close()
   }
   
   /**
    * Writes [value] in [file].
    *
    * @param close optionally closes [file] stream.
    */
   fun write(file: File, value: Tag, close: Boolean = true) = write(file.outputStream(), value, close)
}

/**
 * Creates a NBT Stream using this output stream.
 */
inline fun OutputStream.toNBTStream() = DataOutputStream(FastBufferedOutputStream(GZIPOutputStream(this)))

/**
 * Creates a NBT Stream using this input stream.
 */
inline fun InputStream.toNBTStream() = DataInputStream(FastBufferedInputStream(GZIPInputStream(this)))

/**
 * Writes [tag] in this output stream.
 */
inline fun OutputStream.writeTag(tag: Tag, close: Boolean = true) = TagIO.write(this, tag, close)

/**
 * Reads a [CompoundTag] in this input stream.
 */
inline fun InputStream.readCompound(close: Boolean = true) = TagIO.readCompound(this, close)

/**
 * Writes [tag] in this file.
 */
inline fun File.writeTag(tag: Tag, close: Boolean = true) = TagIO.write(this, tag, close)

/**
 * Reads a [CompoundTag] in this file.
 */
inline fun File.readCompound(close: Boolean = true) = TagIO.readCompound(this, close)

/**
 * Reads a [Tag] in this input stream.
 */
inline fun InputStream.readTag(close: Boolean = true) = TagIO.read(this, close)

/**
 * Reads a [Tag] in this file.
 */
inline fun File.readTag(close: Boolean = true) = TagIO.read(this, close)
