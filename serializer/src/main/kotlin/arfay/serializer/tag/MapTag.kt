package arfay.serializer.tag

/**
 * An abstract implementation of [Tag] that's supports a map of elements.
 */
abstract class MapTag<K, V : Tag> : LinkedHashMap<K, V>, Tag {
   
   constructor() : super()
   constructor(size: Int) : super(size)
   constructor(values: Map<K, V>) : super(values)
   constructor(vararg values: Pair<K, V>) : super(values.toMap())
   
}
