/*
                             MIT License

                        Copyright (c) 2021 uin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package arfay.serializer

import arfay.serializer.common.*
import kotlinx.serialization.*
import kotlinx.serialization.modules.*
import java.io.*

/**
 * An alterable serial format is just a [SerialFormat]
 * with mutable [serializersModule] property.
 */
interface MutableSerialFormat : SerialFormat {
	override var serializersModule: SerializersModule
}

/**
 * An alterable string format is just a [StringFormat]
 * with mutable [serializersModule] property.
 */
interface MutableStringFormat : MutableSerialFormat, StringFormat {
	override var serializersModule: SerializersModule
}

/**
 * An alterable binary format is just a [BinaryFormat]
 * with mutable [serializersModule] property.
 */
interface MutableBinaryFormat : MutableSerialFormat, BinaryFormat {
	override var serializersModule: SerializersModule
}

/**
 * An alterable mark format is just a [TagFormat]
 * with mutable [serializersModule] property.
 */
interface MutableTagFormat : MutableBinaryFormat

/**
 * An alterable stream format is just a [SerialFormat] with mutable
 * [serializersModule] property to use with input/output streams.
 */
interface MutableStreamFormat : MutableSerialFormat {
	override var serializersModule: SerializersModule
	
	fun <T> decodeFrom(input: InputStream, deserializer: DeserializationStrategy<T>): T
	fun <T> encodeTo(output: OutputStream, serializer: SerializationStrategy<T>, value: T)
}

/**
 * An abstract implementation of [MutableSerialFormat].
 */
abstract class AbstractMutableSerialFormat(
	override var serializersModule: SerializersModule,
	open var model: SerialFormat,
) : MutableSerialFormat

/**
 * An abstract implementation of [MutableStringFormat].
 * That's holds a [StringFormat] model to decode/encode strings.
 */
abstract class AbstractMutableStringFormat(
	override var serializersModule: SerializersModule,
	open var model: StringFormat,
) : MutableStringFormat {
	override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T {
		return model.decodeFromString(deserializer, string)
	}
	
	override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
		return model.encodeToString(serializer, value)
	}
}

/**
 * AN abstract implementation of [MutableBinaryFormat].
 * That's holds a [BinaryFormat] model to decode/encode byte arrays.
 */
abstract class AbstractMutableBinaryFormat(
	override var serializersModule: SerializersModule,
	open var model: BinaryFormat,
) : MutableBinaryFormat {
	override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray): T {
		return model.decodeFromByteArray(deserializer, bytes)
	}
	
	override fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
		return model.encodeToByteArray(serializer, value)
	}
}

/**
 * INTERNAL API
 */
internal class MutableSerialFormatImpl(
	module: SerializersModule,
	model: SerialFormat,
) : AbstractMutableSerialFormat(module, model)

/**
 * INTERNAL API
 */
internal class MutableStringFormatImpl(
	module: SerializersModule,
	model: StringFormat,
) : AbstractMutableStringFormat(module, model)

/**
 * INTERNAL API
 */
internal class MutableBinaryFormatImpl(
	module: SerializersModule,
	model: BinaryFormat,
) : AbstractMutableBinaryFormat(module, model)

/**
 * Object instance to create alterable serial formats,
 * such as [MutableSerialFormat], [MutableStringFormat] and finally
 * [MutableBinaryFormat]
 */
object Alterables {
	
	/**
	 * Creates a [MutableSerialFormat] with gived module and model.
	 */
	fun serial(module: SerializersModule, model: SerialFormat): MutableSerialFormat {
		return MutableSerialFormatImpl(module, model)
	}
	
	/**
	 * Creates a [MutableStringFormat] with gived module and model.
	 */
	fun string(module: SerializersModule, model: StringFormat): MutableStringFormat {
		return MutableStringFormatImpl(module, model)
	}
	
	/**
	 * Creates a [MutableBinaryFormat] with gived module and model.
	 */
	fun binary(module: SerializersModule, model: BinaryFormat): MutableBinaryFormat {
		return MutableBinaryFormatImpl(module, model)
	}
}

/**
 * Converts this serial format to a [MutableSerialFormat]
 */
fun SerialFormat.toMutable(module: SerializersModule = FrameworkModule): MutableSerialFormat {
	return Alterables.serial(module, this)
}

/**
 * Converts this string format to a [MutableStringFormat]
 */
fun StringFormat.toMutable(module: SerializersModule = FrameworkModule): MutableStringFormat {
	return Alterables.string(module, this)
}

/**
 * Converts this binary format to a [MutableBinaryFormat]
 */
fun BinaryFormat.toMutable(module: SerializersModule = FrameworkModule): MutableBinaryFormat {
	return Alterables.binary(module, this)
}
