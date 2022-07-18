@file:Suppress("NOTHING_TO_INLINE")

package arfay.graphical.schema

import arfay.core.utils.*
import it.unimi.dsi.fastutil.ints.IntOpenHashSet
import walkmc.graphical.*
import walkmc.graphical.common.*

/**
 * A schema represents how a Scroll graphical
 * should map your content of items.
 *
 * This is an extensive configurable, you can set the start and last slot.
 * Also, can exclude and include certains slots!
 */
typealias Schema = IntSet

/**
 * Creates a schema with this as start and [last] as end.
 */
infix fun Int.schema(last: Int): Schema = (this..last).toSchema()

/**
 * Creates a schema by [row].
 */
fun rowSchema(row: Int): Schema = slotRow(row).toSchema()
fun columnSchema(column: Int): Schema = slotColumn(column).toSchema()

fun rowSchema(vararg rows: Int): Schema {
   val result = IntSet()
   rows.forEach { result += slotRow(it) }
   return result
}

fun columnSchema(vararg columns: Int): Schema {
   val result = IntSet()
   columns.forEach { result += slotColumn(it) }
   return result
}

/**
 * Includes all slots of the specified
 * includer to this schematic.
 */
fun Schema.include(vararg includer: Int): Schema {
   addAll(includer.toHashSet())
   return this
}

/**
 * Includes all slots of the specified
 * includer to this schematic.
 */
infix fun Schema.include(includer: Iterable<Int>): Schema {
   addAll(includer)
   return this
}

/**
 * Includes a specified slot to this schematic.
 */
infix fun Schema.include(slot: Int): Schema {
   add(slot)
   return this
}

/**
 * Includes all slots from [range].
 */
infix fun Schema.include(range: IntRange): Schema {
   addAll(range)
   return this
}

/**
 * Includes all slots of the specified [row].
 */
infix fun Schema.includeRow(row: Int): Schema {
   addAll(slotRow(row))
   return this
}

infix fun Schema.includeColumn(column: Int): Schema {
   addAll(slotColumn(column))
   return this
}

/**
 * Excludes all slots of the specified
 * excluder to this schematic.
 */
fun Schema.exclude(vararg excluder: Int): Schema {
   removeAll(excluder.toHashSet())
   return this
}

/**
 * Excludes all slots of the specified excluder.
 */
infix fun Schema.exclude(excluder: Iterable<Int>): Schema {
   removeAll(excluder.toHashSet())
   return this
}

/**
 * Excludes all slots from [range].
 */
infix fun Schema.exclude(range: IntRange): Schema {
   removeAll(range)
   return this
}

/**
 * Excludes all slots of the specified [row].
 */
infix fun Schema.excludeRow(row: Int): Schema {
   removeAll(slotRow(row))
   return this
}

infix fun Schema.excludeColumn(column: Int): Schema {
   removeAll(slotColumn(column))
   return this
}

/**
 * Excludes a specified slot.
 */
infix fun Schema.exclude(slot: Int): Schema {
   remove(slot)
   return this
}

fun IScrollGraphical.excludeBorder() = schema exclude slotBorder()
fun IScrollGraphical.includeBorder() = schema include slotBorder()


/**
 * Returns the minimum slot that's a
 * graphical user interface can set.
 */
const val MINIMUM_SLOT = 0

/**
 * Returns the maximum slot that's a
 * graphical user interface can set.
 */
const val MAXIMUM_SLOT = 53

/**
 * The defaults' excluder for all schematics.
 */
val DefaultExcluder: Schema = schemaOf(17, 18, 26, 27)

/**
 * The defaults' includer for all schematics.
 */
val DefaultIncluder: Schema = emptySchema()

fun Collection<Int>.toSchema(): Schema = IntSet(this)
fun Iterable<Int>.toSchema(): Schema = IntSet(toSet())
fun Array<Int>.toSchema(): Schema = IntSet(toSet())
fun IntArray.toSchema(): Schema = IntSet(this)
