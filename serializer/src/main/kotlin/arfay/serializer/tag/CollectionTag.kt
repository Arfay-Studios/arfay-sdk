package arfay.serializer.tag

import java.util.*

/**
 * An abstract implementation of [Tag] that's supports a collection of elements.
 */
abstract class CollectionTag<T> : LinkedList<T>, Tag {
   
   constructor() : super()
   constructor(values: Collection<T>) : super(values)
   
   /**
    * The element tag type of this collection.
    */
   abstract fun elementType(): TagType<out Tag>
}
