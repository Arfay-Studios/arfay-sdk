package arfay.graphical.schema

import arfay.core.utils.*

/**
 * The default schematic implementation when mapping
 * a scrollable graphical interface.
 */
fun basicSchema() = 10 schema 34 exclude basicExcluder()

/**
 * An empty schematic implementation when mapping
 * a scrollable graphical interface.
 */
fun emptySchema(): Schema = IntSet()

/**
 * Creates a schema by all [elements].
 */
fun schemaOf(vararg elements: Int): Schema = elements.toSchema()

/**
 * The defaults' excluder for the standard schema.
 */
fun basicExcluder() = schemaOf(17, 18, 26, 27)
