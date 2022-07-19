package arfay.graphical.mapper

import arfay.graphical.*

/**
 * A mapper is a way to modify the order of
 * the scrollable engines of a scrollable graphical interface.
 *
 * You can create your own mapper implementing this interface.
 *
 * @see SingularMapper
 * @see PartialMapper
 */
fun interface Mapper {
   
   /**
    * Maps all scrollable engines to a [Scrollers].
    */
   fun map(graphical: IScrollGraphical, engines: Source): Scrollers
}

/**
 * Creates a new [Mapper] by invoking the specified [block] to map.
 */
inline fun mapping(crossinline block: IScrollGraphical.(Source) -> Scrollers): Mapper {
   return Mapper { graphical, engines -> block(graphical, engines) }
}

/**
 * Creates a new [Mapper] mapping engines by the specified [size]
 */
fun mappingBySize(size: Int, step: Int = 1, partial: Boolean = true): Mapper {
   return mapping { it.windowed(size, step, partial) }
}
