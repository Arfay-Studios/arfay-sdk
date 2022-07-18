package arfay.serializer.tag

import arfay.core.utils.*
import arfay.serializer.tag.types.*

typealias EmptyType = EmptyTag.Type
typealias ByteType = ByteTag.Type
typealias ShortType = ShortTag.Type
typealias IntType = IntTag.Type
typealias LongType = LongTag.Type
typealias FloatType = FloatTag.Type
typealias DoubleType = DoubleTag.Type
typealias ByteArrayType = ByteArrayTag.Type
typealias StringType = StringTag.Type
typealias ListType = ListTag.Type
typealias CompoundType = CompoundTag.Type
typealias IntArrayType = IntArrayTag.Type

/**
 * Represents a registry for all tag types.
 */
object TagRegistry {
   private val REGISTRY = ByteObjectMap<TagType<out Tag>>(12)
   
   init {
      registerVanilla()
   }
   
   /**
    * Gets a tag type by id.
    */
   operator fun get(id: Byte): TagType<out Tag> = REGISTRY[id] ?: EmptyType
   
   /**
    * Register [type] to the registry.
    */
   fun registerType(type: TagType<out Tag>) {
      REGISTRY.putIfAbsent(type.id, type)
   }
   
   /**
    * Verify if [type] is registered as tag.
    */
   fun isRegistered(type: TagType<out Tag>): Boolean {
      return type.id in REGISTRY
   }
   
   /**
    * Verify if type [id] is registered as tag.
    */
   fun isRegistered(id: Byte): Boolean {
      return id in REGISTRY
   }
   
   /**
    * Registers all vanilla default tag types.
    */
   private fun registerVanilla() {
      registerType(EmptyType)
      registerType(ByteType)
      registerType(ShortType)
      registerType(IntType)
      registerType(LongType)
      registerType(FloatType)
      registerType(DoubleType)
      registerType(ByteArrayType)
      registerType(StringType)
      registerType(ListType)
      registerType(CompoundType)
      registerType(IntArrayType)
   }
}
