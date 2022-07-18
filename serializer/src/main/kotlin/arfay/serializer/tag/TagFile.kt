package arfay.serializer.tag

import arfay.core.utils.*
import arfay.serializer.*
import arfay.serializer.common.*
import kotlinx.serialization.*
import java.io.*

/**
 * The serial file for coding with Tag files.
 * Also loads the file when constructs a new instance of this class.
 */
open class TagFile<T : Any>(
   override var file: File,
   override var model: T,
   override var serial: KSerializer<T> = model::class.serializer().cast(),
   override var format: MutableTagFormat = TagFormat,
) : TagSerialFile<T> {
   override var data: T = model
   override var observers: Observers<T> = enumMap()
   
   init {
      load()
   }
}
