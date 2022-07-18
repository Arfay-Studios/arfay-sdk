package arfay.serializer.tag

import arfay.serializer.tag.types.*

/**
 * Represents an object that's can be storable in NBT form.
 */
interface Storable {
   
   /**
    * Saves this storable object in [tag].
    */
   fun save(tag: CompoundTag)
   
   /**
    * Loads this storable object from [tag].
    */
   fun load(tag: CompoundTag)
   
   /**
    * Creates a new [CompoundTag] with all data of this storable object.
    */
   fun toTag(): CompoundTag {
      val tag = CompoundTag()
      save(tag)
      return tag
   }
   
   /**
    * Encodes this storable object to a ByteArray.
    */
   fun toByteArray(): ByteArray {
      return toTag().toByteArray()
   }
}
