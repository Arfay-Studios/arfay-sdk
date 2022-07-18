package arfay.serializer.tag

import arfay.serializer.*
import arfay.serializer.common.*
import it.unimi.dsi.fastutil.io.*
import kotlinx.serialization.*
import kotlinx.serialization.modules.*
import java.io.*

/**
 * The serial format used by tag files. The tag files don't have any extra configuration
 * so no need to the object being a class.
 */
object TagFormat : MutableTagFormat {
   override var serializersModule: SerializersModule = FrameworkModule
   
   override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray): T {
      return FastByteArrayInputStream(bytes).decodeTag(deserializer)
   }
   
   override fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
      val output = FastByteArrayOutputStream()
      output.encodeTag(serializer, value)
      return output.array
   }
   
   fun <T> decodeFromStream(stream: InputStream, serializer: DeserializationStrategy<T>) {
      stream.decodeTag(serializer)
   }
   
   fun <T> encodeToStream(stream: OutputStream, serializer: SerializationStrategy<T>, value: T) {
      stream.encodeTag(serializer, value)
   }
   
   fun <T> decodeFromFile(file: File, serializer: DeserializationStrategy<T>) {
      file.decodeTag(serializer)
   }
   
   fun <T> encodeToFile(file: File, serializer: SerializationStrategy<T>, value: T) {
      file.encodeTag(serializer, value)
   }
}

inline fun <reified T> TagFormat.decodeFromStream(stream: InputStream): T {
   return stream.decodeTag(serializer())
}

inline fun <reified T> TagFormat.encodeToStream(stream: OutputStream, value: T) {
   stream.encodeTag(serializer(), value)
}

inline fun <reified T> TagFormat.decodeFromFile(file: File): T {
   return file.decodeTag(serializer())
}

inline fun <reified T> TagFormat.encodeToFile(file: File, value: T) {
   file.encodeTag(serializer(), value)
}
