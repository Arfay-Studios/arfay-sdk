package arfay.serializer.tag.types

import arfay.core.utils.*
import arfay.serializer.serial.*
import arfay.serializer.tag.*
import kotlinx.serialization.Serializable
import kotlin.contracts.*
import java.io.*

/**
 * An implementation of [CollectionTag] as list.
 */
@Serializable(ListTagSerializer::class)
open class ListTag<T : Tag> : CollectionTag<T> {
   
   var elementType: TagType<out Tag>? = null
   
   override val type
      get() = Type
   
   constructor()
   constructor(values: Iterable<T>, type: TagType<out Tag>? = null) {
      addAll(values)
      elementType = type
   }
   
   constructor(values: Collection<T>, type: TagType<out Tag>? = null) : super(values) {
      elementType = type
   }
   
   override fun write(data: DataOutput) {
      data.writeByte(elementType().id.toInt())
      data.writeInt(size)
      for (element in this) {
         element.write(data)
      }
   }
   
   override fun elementType(): TagType<out Tag> {
      if (elementType == null) {
         elementType = if (isEmpty()) EmptyType else first.type
      }
      
      return elementType!!
   }
   
   override fun copy(): ListTag<T> = ListTag(this)
   
   override fun toString(): String = buildString {
      append('[')
      this@ListTag.forEachIndexed { index, tag ->
         if (index != 0) append(',')
         append(index).append(':').append(tag)
      }
      append(']')
   }
   
   /**
    * Tag type of [List].
    */
   object Type : TagType<ListTag<out Tag>> {
      override val id: Byte get() = 9
      override val supportItems: Boolean get() = true
      
      override fun load(data: DataInput): ListTag<out Tag> {
         val type = TagRegistry[data.readByte()]
         val size = data.readInt()
         val list = ArrayList<Tag>(size)
         repeat(size) {
            list += type.load(data)
         }
         
         return ListTag(list)
      }
   }
   
   companion object {
      
      /**
       * Converts [data] in a NBT List.
       */
      fun <T : Tag> fromByteArray(data: ByteArray): ListTag<T> {
         return data.decodeTag().cast()
      }
   }
}

/**
 * Creates an empty [ListTag].
 */
fun <T : Tag> tagListOf() = ListTag<T>()

/**
 * Creates a [ListTag] with all [values].
 */
fun tagListOf(vararg values: Tag) = ListTag(values.toMutableList())

/**
 * Creates a [ListTag] with values of this list.
 */
fun Collection<Tag>.toTag() = ListTag(this)

/**
 * Creates a [ListTag] with values of this list.
 */
fun Iterable<Tag>.toTag() = ListTag(this)

/**
 * Populates a newly created [ListTag].
 */
@OptIn(ExperimentalContracts::class)
inline fun <T : Tag> tagList(builder: ListTag<T>.() -> Unit): ListTag<T> {
   contract { callsInPlace(builder, InvocationKind.EXACTLY_ONCE) }
   return ListTag<T>().apply(builder)
}
