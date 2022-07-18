@file:Suppress("NOTHING_TO_INLINE")

package arfay.serializer.tag.types

import arfay.core.utils.*
import arfay.serializer.serial.*
import arfay.serializer.tag.*
import arfay.serializer.tag.adapter.*
import kotlinx.serialization.Serializable
import kotlin.contracts.*
import java.io.*
import java.util.*

/**
 * An implementation of [MapTag] as compound.
 */
@Serializable(CompoundTagSerializer::class)
open class CompoundTag : MapTag<String, Tag> {
   
   override val type
      get() = Type
   
   constructor()
   constructor(size: Int) : super(size)
   constructor(values: Map<String, Tag>) : super(values)
   constructor(vararg values: Pair<String, Tag>) : super(*values)
   
   override fun write(data: DataOutput) {
      for ((key, value) in this) {
         writeEntry(key, value, data)
      }
      data.writeByte(0) // end
   }
   
   override fun copy() = CompoundTag(this)
   
   override fun toString(): String = buildString {
      append('{')
      for (entry in this@CompoundTag) {
         if (length != 1) append(", ")
         append(entry.key).append(':').append(entry.value)
      }
      append('}')
   }
   
   /**
    * Gets a type-safe tag [T] in the given [key]
    */
   inline fun <reified T : Tag> getTag(key: String): T {
      return (get(key) ?: error("No NBT element in CompoundTag found with key $key")).cast()
   }
   
   /**
    * Gets a type-safe tag [T] in the given [key] or returns null
    */
   inline fun <reified T : Tag> getTagOrNull(key: String): T? {
      return get(key) as? T
   }
   
   fun has(key: String): Boolean {
      return containsKey(key)
   }
   
   fun has(key: String, id: Int): Boolean {
      return (get(key)?.type ?: return false).id == id.toByte()
   }
   
   fun has(key: String, type: TagType<out Tag>): Boolean {
      return (get(key)?.type ?: return false) === type
   }
   
   fun typeOf(key: String): TagType<out Tag> {
      return get(key)?.type ?: EmptyType
   }
   
   fun typeIdOf(key: String): Int {
      return typeOf(key).id.toInt()
   }
   
   fun isNumber(key: String): Boolean {
      return get(key) is NumberTag
   }
   
   fun number(key: String, default: Number = 0): Number {
      val tag = get(key) ?: return default
      return if (tag is NumberTag) tag.toNumber() else default
   }
   
   fun numberOrNull(key: String): Number? {
      val tag = get(key) ?: return null
      return if (tag is NumberTag) tag.toNumber() else null
   }
   
   fun byte(key: String, default: Byte = 0): Byte {
      return (getTagOrNull<ByteTag>(key) ?: return default).value
   }
   
   fun byteOrNull(key: String): Byte? {
      return (getTagOrNull<ByteTag>(key) ?: return null).value
   }
   
   fun boolean(key: String, default: Boolean = false): Boolean {
      val value = byteOrNull(key) ?: return default
      return value == 1.toByte()
   }
   
   fun booleanOrNull(key: String): Boolean? {
      val value = byteOrNull(key) ?: return null
      return value == 1.toByte()
   }
   
   fun short(key: String, default: Short = 0): Short {
      return (getTagOrNull<ShortTag>(key) ?: return default).value
   }
   
   fun shortOrNull(key: String): Short? {
      return (getTagOrNull<ShortTag>(key) ?: return null).value
   }
   
   fun int(key: String, default: Int = 0): Int {
      return (getTagOrNull<IntTag>(key) ?: return default).value
   }
   
   fun intOrNull(key: String): Int? {
      return (getTagOrNull<IntTag>(key) ?: return null).value
   }
   
   fun long(key: String, default: Long = 0): Long {
      return (getTagOrNull<LongTag>(key) ?: return default).value
   }
   
   fun longOrNull(key: String): Long? {
      return (getTagOrNull<LongTag>(key) ?: return null).value
   }
   
   fun float(key: String, default: Float = 0f): Float {
      return (getTagOrNull<FloatTag>(key) ?: return default).value
   }
   
   fun floatOrNull(key: String): Float? {
      return (getTagOrNull<FloatTag>(key) ?: return null).value
   }
   
   fun double(key: String, default: Double = 0.0): Double {
      return (getTagOrNull<DoubleTag>(key) ?: return default).value
   }
   
   fun doubleOrNull(key: String): Double? {
      return (getTagOrNull<DoubleTag>(key) ?: return null).value
   }
   
   fun byteArray(key: String, default: ByteArray? = null): ByteArray {
      return (getTagOrNull<ByteArrayTag>(key) ?: return default ?: ByteArray(0)).value
   }
   
   fun byteArrayOrNull(key: String): ByteArray? {
      return (getTagOrNull<ByteArrayTag>(key) ?: return null).value
   }
   
   fun string(key: String, default: String = ""): String {
      return (getTagOrNull<StringTag>(key) ?: return default).value
   }
   
   fun stringOrNull(key: String): String? {
      return (getTagOrNull<StringTag>(key) ?: return null).value
   }
   
   fun <T : Tag> list(key: String, default: ListTag<T>? = null): ListTag<T> {
      return getTagOrNull(key) ?: return default ?: ListTag()
   }
   
   fun <T : Tag> listOrNull(key: String): ListTag<T>? {
      return getTagOrNull(key)
   }
   
   fun stringList(key: String, default: ListTag<StringTag>? = null): ListTag<StringTag> {
      return getTagOrNull(key) ?: return default ?: ListTag()
   }
   
   fun stringListOrNull(key: String): ListTag<StringTag>? {
      return getTagOrNull(key)
   }
   
   fun compoundList(key: String, default: ListTag<CompoundTag>? = null): ListTag<CompoundTag> {
      return getTagOrNull(key) ?: return default ?: ListTag()
   }
   
   fun compoundListOrNull(key: String): ListTag<CompoundTag>? {
      return getTagOrNull(key)
   }
   
   fun compound(key: String, default: CompoundTag? = null): CompoundTag {
      return getTagOrNull(key) ?: return default ?: CompoundTag()
   }
   
   fun compoundOrNull(key: String): CompoundTag? {
      return getTagOrNull(key)
   }
   
   fun intArray(key: String, default: IntArray? = null): IntArray {
      return (getTagOrNull<IntArrayTag>(key) ?: return default ?: IntArray(0)).value
   }
   
   fun intArrayOrNull(key: String): IntArray? {
      return (getTagOrNull<IntArrayTag>(key) ?: return null).value
   }
   
   fun uuid(key: String, default: UUID? = null): UUID {
      return adapted(key, UUIDAdapter, default)
   }
   
   fun uuidOrNull(key: String): UUID? {
      return adaptedOrNull(key, UUIDAdapter)
   }
   
   /**
    * Gets an adapted value in the given [key] of this tag compound.
    *
    * @return the adapted value in [key] or [default]
    */
   fun <T : Any> adapted(key: String, adapter: TagAdapter<T>, default: T? = null): T {
      return catching(default ?: error("No default NBT found while getting adapted value")) {
         adapter.read(key, this, default)
      }
   }
   
   /**
    * Gets an adapted value in the given [key] of this tag compound.
    *
    * @return the adapted value in [key] or null
    */
   fun <T : Any> adaptedOrNull(key: String, adapter: TagAdapter<T>): T? {
      return catchingOrNull(null) {
         adapter.read(key, this, null)
      }
   }
   
   operator fun set(key: String, value: Boolean) = put(key, ByteTag.of(value))
   operator fun set(key: String, value: Byte) = put(key, ByteTag.of(value))
   operator fun set(key: String, value: Short) = put(key, ShortTag.of(value))
   operator fun set(key: String, value: Int) = put(key, IntTag.of(value))
   operator fun set(key: String, value: Long) = put(key, LongTag.of(value))
   operator fun set(key: String, value: Float) = put(key, FloatTag.of(value))
   operator fun set(key: String, value: Double) = put(key, DoubleTag.of(value))
   operator fun set(key: String, value: ByteArray) = put(key, ByteArrayTag(value))
   operator fun set(key: String, value: String) = put(key, StringTag.of(value))
   operator fun set(key: String, value: IntArray) = put(key, IntArrayTag(value))
   operator fun <T : Any> set(key: String, adapter: TagAdapter<T>, value: T) = adapter.write(key, this, value)
   operator fun set(key: String, value: UUID) = set(key, UUIDAdapter, value)
   
   private fun writeEntry(key: String, tag: Tag, data: DataOutput) {
      data.writeByte(tag.id)
      if (tag.id != 0) {
         data.writeUTF(key)
         tag.write(data)
      }
   }
   
   /**
    * Tag type of [CompoundTag].
    */
   object Type : TagType<CompoundTag> {
      override val id: Byte get() = 10
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): CompoundTag {
         val map = LinkedHashMap<String, Tag>()
         while (true) {
            when (val id = data.readByte().toInt()) {
               0 -> break
               else -> readEntry(id, data.readUTF(), data, map)
            }
         }
         
         return CompoundTag(map)
      }
      
      fun readEntry(id: Int, key: String, data: DataInput, map: MutableMap<String, Tag>): Tag {
         val type = TagRegistry[id.toByte()]
         
         return try {
            val tag = type.load(data)
            map[key] = tag
            tag
         } catch (_: Exception) {
            describeError(id, key)
         }
      }
      
      private fun describeError(id: Int, key: String): Nothing {
         throw Exception(
            """
            Error while loading NBT tag. (Corrupt file?)
            NBT Id: $id
            NBT Key: $key
         """.trimIndent()
         )
      }
   }
   
   companion object {
      
      /**
       * Converts [data] in a NBT compound.
       */
      fun fromByteArray(data: ByteArray): CompoundTag {
         return data.decodeCompound()
      }
   }
}

/**
 * Creates an empty [CompoundTag].
 */
inline fun compoundOf() = CompoundTag()

/**
 * Creates a [CompoundTag] with all [values].
 */
inline fun compoundOf(vararg values: Pair<String, Tag>) = CompoundTag(*values)

/**
 * Populates a newly created [CompoundTag].
 */
@OptIn(ExperimentalContracts::class)
inline fun compound(builder: CompoundTag.() -> Unit): CompoundTag {
   contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
   return CompoundTag().apply(builder)
}
