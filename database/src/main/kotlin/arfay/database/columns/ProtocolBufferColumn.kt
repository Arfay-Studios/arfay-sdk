/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/
package arfay.database.columns

import arfay.core.utils.*
import arfay.serializer.*
import arfay.serializer.common.*
import kotlinx.serialization.*
import kotlinx.serialization.modules.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.vendors.*
import kotlin.reflect.*

/**
 * A skeletal model for creating new column types using Protocol Buffers.
 * @see ProtobufLimitedColumnType
 * @see ProtobufUnlimitedColumnType
 */
abstract class ProtobufColumnType<T : Any>(
	type: KClass<T>,
	val module: SerializersModule = FrameworkModule
) : BasicBinaryColumnType() {
	val serialType = module.serializer(type.java)
	
	override fun notNullValueToDB(value: Any): ByteArray {
		return ProtobufStrategy.encodeToByteArray(serialType, value.cast())
	}
	
	override fun nonNullValueToString(value: Any): String {
		val string = ProtobufStrategy.encodeToByteArray(serialType, value.cast())
		return "'$string'"
	}
	
	override fun valueFromDB(value: Any): T = when (value) {
		is ByteArray -> ProtobufStrategy.decodeFromByteArray(serialType, value).cast()
		else -> value.cast()
	}
}

/**
 * A [ProtobufLimitedColumnType] represents a column thats has your data
 * as Protocol Buffer. The protocol buffer data is a limited binary type, this is,
 * similar to [JsonVarcharColumnType] thats holds a specified length.
 * If you need more space use [ProtobufUnlimitedColumnType].
 */
open class ProtobufLimitedColumnType<T : Any>(
	val length: Int,
	type: KClass<T>,
	module: SerializersModule = FrameworkModule
) : ProtobufColumnType<T>(type, module) {
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
 * A [ProtobufUnlimitedColumnType] represents a column thats has your data
 * as Protocol Buffer. The protocol buffer data is a unlimited binary type, this is,
 * similar to [JsonTextColumnType] thats holds a unlimited length.
 */
open class ProtobufUnlimitedColumnType<T : Any>(
	type: KClass<T>,
	module: SerializersModule = FrameworkModule
) : ProtobufColumnType<T>(type, module)

/**
 * Registers a protocol buffer limited column, with specified name, length
 * and type in this table. This used reified generic type to get the [T] class.
 */
inline fun <reified T : Any> Table.protobuf(
	name: String,
	length: Int,
	module: SerializersModule = FrameworkModule
): Column<T> = registerColumn(name, ProtobufLimitedColumnType(length, T::class, module))


/**
 * Registers a protocol buffer unlimited column, with specified name and type
 * in this table. This used reified generic type to get the [T] class.
 */
inline fun <reified T : Any> Table.protobuf(
	name: String,
	module: SerializersModule = FrameworkModule
): Column<T> = registerColumn(name, ProtobufUnlimitedColumnType(T::class, module))

