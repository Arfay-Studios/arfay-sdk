/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.database.columns

import arfay.serializer.common.*
import kotlinx.serialization.*
import kotlinx.serialization.modules.*
import net.arfay.sdk.database.util.cast
import net.arfay.sdk.serializer.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.vendors.*
import walkmc.serializer.common.*
import kotlin.reflect.*

/**
 * A skeletal model for creating new column types using JSON.
 * @see JsonVarcharColumnType
 * @see JsonTextColumnType
 */
abstract class JsonColumnType<T : Any>(
	type: KClass<T>,
	val module: SerializersModule = FrameworkModule
) : StringColumnType() {
	val serialType by lazy { module.serializer(type.java) }
	
	override fun notNullValueToDB(value: Any) = JsonStrategyDatabase.encodeToString(serialType, value.cast())
	override fun nonNullValueToString(value: Any): String {
		val string = JsonStrategyDatabase.encodeToString(serialType, value.cast())
		return "'$string'"
	}
	
	override fun valueFromDB(value: Any): T = when (value) {
		is String -> JsonStrategyDatabase.decodeFromString(serialType, value).cast()
		else -> value.cast()
	}
}

/**
 * A [JsonVarcharColumnType] represents a column thats has your data as JSON.
 * The json data is VARCHAR, this is, as a limited amount of length.
 * If you need more length, use [JsonTextColumnType].
 */
open class JsonVarcharColumnType<T : Any>(
	val length: Int,
	type: KClass<T>,
	module: SerializersModule = FrameworkModule
) : JsonColumnType<T>(type, module) {
	override fun sqlType(): String = "VARCHAR($length)"
}

/**
 * A [JsonTextColumnType] represents a column thats has your data as JSON.
 * The json data is TEXT, this is, as a unlimited amount of length.
 */
open class JsonTextColumnType<T : Any>(
	type: KClass<T>,
	module: SerializersModule = FrameworkModule
) : JsonColumnType<T>(type, module) {
	override fun sqlType(): String = currentDialect.dataTypeProvider.textType()
}

/**
 * Registers a json VARCHAR column, with specified name, length
 * and type in this table. This used reified generic type to get the [T] class.
 */
inline fun <reified T : Any> Table.json(
	name: String,
	length: Int,
	module: SerializersModule = FrameworkModule
): Column<T> = registerColumn(name, JsonVarcharColumnType(length, T::class, module))


/**
 * Registers a json TEXT column, with specified name and type
 * in this table. This used reified generic type to get the [T] class.
 */
inline fun <reified T : Any> Table.json(
	name: String,
	module: SerializersModule = FrameworkModule
): Column<T> = registerColumn(name, JsonTextColumnType(T::class, module))

