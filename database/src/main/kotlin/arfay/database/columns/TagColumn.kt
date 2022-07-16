/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.database.columns

import arfay.serializer.common.*
import arfay.serializer.tag.*
import kotlinx.serialization.*
import kotlinx.serialization.modules.*
import net.arfay.sdk.database.util.cast
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.vendors.*
import kotlin.reflect.*

/**
 * A skeletal model for creating new column types using [Tag].
 * @see TagLimitedColumnType
 * @see TagUnlimitedColumnType
 */
abstract class TagColumnType<T : Any>(
	type: KClass<T>,
	val module: SerializersModule = FrameworkModule
) : BasicBinaryColumnType() {
	val serialType by lazy { module.serializer(type.java) }
	
	override fun notNullValueToDB(value: Any): ByteArray {
		return TagFormat.encodeToByteArray(serialType, value.cast())
	}
	
	override fun nonNullValueToString(value: Any): String {
		val string = TagFormat.encodeToByteArray(serialType, value.cast())
		return "'$string'"
	}
	
	override fun valueFromDB(value: Any): T = when (value) {
		is ByteArray -> TagFormat.decodeFromByteArray(serialType, value).cast()
		else -> value.cast()
	}
}

/**
 * A [TagLimitedColumnType] represents a column thats has your data
 * as [Tag]. The mark data is a limited binary type, this is,
 * similar to [JsonVarcharColumnType] thats holds a specified length.
 * If you need more space use [TagUnlimitedColumnType].
 */
open class TagLimitedColumnType<T : Any>(
	val length: Int,
	type: KClass<T>,
	module: SerializersModule = FrameworkModule
) : TagColumnType<T>(type, module) {
	override fun sqlType(): String = currentDialect.dataTypeProvider.binaryType(length)
	
	override fun validateValueBeforeUpdate(value: Any?) {
		if (value is ByteArray) {
			require(value.size <= length) {
				"Value '$value' can't be stored to database column because exceeds length ($length)"
			}
		}
	}
}

/**
 * A [TagUnlimitedColumnType] represents a column thats has your data
 * as [Tag]. The mark data is a unlimited binary type, this is,
 * similar to [JsonTextColumnType] thats holds a unlimited length.
 */
open class TagUnlimitedColumnType<T : Any>(
	type: KClass<T>,
	module: SerializersModule = FrameworkModule
) : TagColumnType<T>(type, module)

/**
 * Registers a mark limited column, with specified name, length and
 * type in this table. This use reified generic type to get the [T] class.
 */
inline fun <reified T : Any> Table.tag(
	name: String,
	length: Int,
	module: SerializersModule = FrameworkModule
): Column<T> = registerColumn(name, TagLimitedColumnType(length, T::class, module))


/**
 * Registers a mark unlimited column, with specified name and type
 * in this table. This use reified generic type to get the [T] class.
 */
inline fun <reified T : Any> Table.tag(
	name: String,
	module: SerializersModule = FrameworkModule
): Column<T> = registerColumn(name, TagUnlimitedColumnType(T::class, module))

