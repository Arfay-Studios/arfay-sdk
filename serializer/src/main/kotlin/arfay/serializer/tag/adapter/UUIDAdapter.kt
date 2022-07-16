package arfay.serializer.tag.adapter

import arfay.serializer.tag.types.*
import net.arfay.sdk.utils.*
import java.util.*

/**
 * [UUID] adapter for NBT.
 */
object UUIDAdapter : TagAdapter<UUID> {
   override fun write(key: String, tag: CompoundTag, value: UUID) {
      tag[key] = value.toString()
   }
   
   override fun read(key: String, tag: CompoundTag, default: UUID?): UUID {
      return uuid(tag.string(key))
   }
}
