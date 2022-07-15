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
import net.arfay.sdk.extensions.*
import net.arfay.sdk.serializer.strategy.*
import net.benwoodworth.knbt.*
import walkmc.serializer.common.*
import walkmc.serializer.formatter.*
import java.io.*

/**
 * The default Named Binary Tag (NBT) format.
 * This contains [FrameworkModule] as main module.
 * This also is lazy init.
 */
val NBT by lazy {
	Alterables.binary(FrameworkModule,
		Nbt {
			variant = NbtVariant.Java
			compression = NbtCompression.Gzip
			encodeDefaults = true
			compressionLevel = 3
			serializersModule = FrameworkModule
		}
	)
}

/**
 * The default Named Binary Tag (NBT) format.
 * This contains [FrameworkModule] as main module and
 * [ColorStrategy] as backend strategy encoder/decoder.
 * This also is lazy init.
 */
val NBTStrategy by lazy {
	StrategyBinaryFormatter(NBT, ColorStrategy, ColorStrategy)
}

/**
 * The serial file for coding with Named Binary Tag (NBT) files.
 * By default [format] is [NBTStrategy].
 * Also loads the file when constructs a new instance of this class.
 */
@OptIn(InternalSerializationApi::class)
open class NBTFile<T : Any>(
	override var file: File,
	override var model: T,
	override var serial: KSerializer<T> = model::class.serializer().cast(),
	override var format: AlterableBinaryFormat = NBTStrategy,
) : BinarySerialFile<T> {
	override var data = model
	override var observers: Observers<T> = Observers()
	
	init {
		load()
	}
}

/**
 * Represents a binary folder compost only with named binary tag serial files.
 * All named binary tag files loaded by the folder is compost of the model [T].
 */
open class NBTFolder<T : Any>(folder: File, model: T) : BinaryFolder<T>(folder, model) {
	init {
		implementsAll()
	}
	
	override fun implement(file: String, model: T) {
		implement(NBTFile(File(folder, "$file.dat"), model))
	}
	
	override fun search() = folder
		.files { extension == "dat" }
		.map { NBTFile(it, model) }
}
