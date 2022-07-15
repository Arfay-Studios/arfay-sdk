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

package net.arfay.sdk.serializer

import kotlinx.serialization.*
import kotlinx.serialization.protobuf.*
import net.arfay.sdk.extensions.*
import net.arfay.sdk.serializer.strategy.*
import walkmc.serializer.common.*
import walkmc.serializer.formatter.*
import java.io.*


/**
 * The default Protocol Buffer format.
 * This contains [FrameworkModule] as main module.
 * This also is lazy init.
 */
@OptIn(ExperimentalSerializationApi::class)
val Protobuf by lazy {
	Alterables.binary(FrameworkModule,
		ProtoBuf {
			encodeDefaults = true
			serializersModule = FrameworkModule
		}
	)
}

/**
 * The default Protocol Buffer format.
 * This contains [FrameworkModule] as main module and
 * [ColorStrategy] as backend strategy encoder/decoder.
 * This also is lazy init.
 */
val ProtobufStrategy by lazy {
	StrategyBinaryFormatter(Protobuf, ColorStrategy, ColorStrategy)
}

/**
 * The serial file for coding with Protocol Buffers files.
 * By default [format] is [ProtobufStrategy].
 * Also loads the file when constructs a new instance of this class.
 */
@OptIn(InternalSerializationApi::class)
open class ProtobufFile<T : Any>(
	override var file: File,
	override var model: T,
	override var serial: KSerializer<T> = model::class.serializer().cast(),
	override var format: AlterableBinaryFormat = ProtobufStrategy,
) : BinarySerialFile<T> {
	override var data = model
	override var observers: Observers<T> = Observers()
	
	init {
		load()
	}
}

/**
 * Represents a binary folder compost only with protocol buffer serial files.
 * All protocol buffer files loaded by the folder is compost of the model [T].
 */
open class ProtobufFolder<T : Any>(folder: File, model: T) : BinaryFolder<T>(folder, model) {
	init {
		implementsAll()
	}
	
	override fun implement(file: String, model: T) {
		implement(ProtobufFile(File(folder, "$file.proto"), model))
	}
	
	override fun search() = folder
		.files { extension == "proto" }
		.map { ProtobufFile(it, model) }
}
