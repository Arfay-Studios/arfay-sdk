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

import arfay.core.utils.*
import arfay.serializer.common.*
import arfay.serializer.common.files
import arfay.serializer.formatter.*
import arfay.serializer.strategy.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.*
import kotlin.collections.map
import java.io.*

/**
 * Shortcut for creating a [Json] component.
 */
fun json(
	module: SerializersModule = FrameworkModule,
	prettyPrint: Boolean = true,
	printIndent: String = "  ",
	encodeDefaults: Boolean = true,
) = Json {
   this.prettyPrint = prettyPrint
   this.prettyPrintIndent = printIndent
   this.encodeDefaults = encodeDefaults
   this.serializersModule = module
}

/**
 * The default JSON format.
 * This contains [FrameworkModule] as main module.
 * This also is lazy init.
 */
val Json by lazy {
   Alterables.string(
      FrameworkModule,
      Json {
         prettyPrint = true
         prettyPrintIndent = "  "
         encodeDefaults = true
         serializersModule = FrameworkModule
      }
   )
}

/**
 * The default JSON save format.
 * This contains [FrameworkModule] as main module.
 * This also is lazy init.
 *
 * ### Note:
 * This should only use to save data such as
 * database or in file. Not use this to settings.
 */
val JsonDatabase by lazy {
   Alterables.string(
      FrameworkModule,
      Json {
         encodeDefaults = true
         allowStructuredMapKeys = true
         serializersModule = FrameworkModule
      }
   )
}

/**
 * The default JSON format.
 * This contains [FrameworkModule] as main module and
 * [ColorStrategy] as backend strategy encoder/decoder.
 * This also is lazy init.
 */
val JsonStrategy by lazy {
   StrategyStringFormatter(Json, ColorStrategy, ColorStrategy)
}

/**
 * The default JSON save format.
 * This contains [FrameworkModule] as main module and
 * [ColorStrategy] as backend strategy encoder/decoder.
 * This also is lazy init.
 *
 * ### Note:
 * This should only use to save data such as
 * database or in file. Not use this to settings.
 */
val JsonStrategyDatabase by lazy {
   StrategyStringFormatter(JsonDatabase, ColorStrategy, ColorStrategy)
}

/**
 * The serial file for coding with JSON files.
 * By default [format] is [JsonStrategy].
 * Also loads the file when constructs a new instance of this class.
 */
open class JsonFile<T : Any>(
   override var file: File,
   override var model: T,
   override var serial: KSerializer<T> = model::class.serializer().cast(),
   override var format: MutableStringFormat = JsonStrategy,
) : StringSerialFile<T> {
   override var data: T = model
   override var observers: Observers<T> = enumMap()
   
   init {
      load()
   }
}

/**
 * Represents a string folder compost only with json serial files.
 * All json files loaded by the folder is compost of the model [T].
 */
open class JsonFolder<T : Any>(folder: File, model: T) : StringFolder<T>(folder, model) {
   init {
      implementsAll()
   }
   
   override fun implement(file: String, model: T) {
      implement(JsonFile(File(folder, "$file.json"), model))
   }
   
   override fun search() = folder
      .files { extension == "json" }
      .map { JsonFile(it, model) }
}
