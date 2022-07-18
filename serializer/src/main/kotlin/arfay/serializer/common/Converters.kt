package arfay.serializer.common

import arfay.serializer.*
import kotlinx.serialization.*

/**
 * Converts this string to a value from a JSON string.
 * Note that the string must be a JSON string.
 */
@OptIn(InternalSerializationApi::class)
inline fun <reified T : Any> String.convertFromJson(serializer: KSerializer<T> = T::class.serializer()): T {
   return JsonStrategy.decodeFromString(serializer, this)
}

/**
 * Converts this string to a value from a YAML string.
 * Note that the string must be a YAML string.
 */
@OptIn(InternalSerializationApi::class)
inline fun <reified T : Any> String.convertFromYaml(serializer: KSerializer<T> = T::class.serializer()): T {
   return YamlStrategy.decodeFromString(serializer, this)
}

/**
 * Converts this byte array to a value from a Protobuf binary type.
 * Note that the byte array must be a protobuf binary type.
 */
@OptIn(InternalSerializationApi::class)
inline fun <reified T : Any> ByteArray.convertFromProtobuf(serializer: KSerializer<T> = T::class.serializer()): T {
   return ProtobufStrategy.decodeFromByteArray(serializer, this)
}

/**
 * Converts this byte array to a value from a NBT byte array.
 * Note that the byte array must be a nbt byte arrat.
 */
/*
inline fun <reified T : Any> ByteArray.convertFromNBT(serializer: KSerializer<T> = T::class.serializer()): T {
   return Nbt.decodeFromByteArray(serializer, this)
}
 */
