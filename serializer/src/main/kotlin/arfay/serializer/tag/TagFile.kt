package arfay.serializer.tag

import arfay.serializer.*
import kotlinx.serialization.*
import net.arfay.sdk.extensions.*
import walkmc.serializer.common.*
import java.io.*

/**
 * The serial file for coding with Tag files.
 * Also loads the file when constructs a new instance of this class.
 */
open class TagFile<T : Any>(
   override var file: File,
   override var model: T,
   override var serial: KSerializer<T> = model::class.serializer().cast(),
   override var format: MutableTagFormat = TagFormat(),
) : TagSerialFile<T> {
   override var data: T = model
   override var observers: Observers<T> = Observers()
   
   init {
      load()
   }
}
