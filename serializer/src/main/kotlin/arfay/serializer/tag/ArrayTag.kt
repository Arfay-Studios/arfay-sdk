package arfay.serializer.tag

/**
 * An abstract implementation of [Tag] that's supports a collection of elements.
 */
interface ArrayTag : Tag {
   
   /**
    * The element tag type of this array.
    */
   fun elementType(): TagType<out Tag>
}
