@file:Suppress("NOTHING_TO_INLINE")

package arfay.serializer.common

import arfay.serializer.serial.*
import kotlinx.serialization.descriptors.*

inline fun StringDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.STRING)
inline fun BooleanDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.BOOLEAN)
inline fun CharDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.CHAR)
inline fun ByteDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.BYTE)
inline fun ShortDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.SHORT)
inline fun IntDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.INT)
inline fun LongDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.LONG)
inline fun FloatDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.FLOAT)
inline fun DoubleDescriptor(name: String) = PrimitiveSerialDescriptor(name, PrimitiveKind.DOUBLE)

/**
 * Build a new class descriptor with [block].
 */
fun descriptor(
	name: String,
	vararg typeParameters: SerialDescriptor,
	block: ClassSerialDescriptorBuilder.() -> Unit
): SerialDescriptor = buildClassSerialDescriptor(name, *typeParameters, builderAction = block)

/**
 * A string version of [element].
 */
fun ClassSerialDescriptorBuilder.string(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<String>(elementName, annotations, isOptional)

/**
 * A char version of [element].
 */
fun ClassSerialDescriptorBuilder.char(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Char>(elementName, annotations, isOptional)

/**
 * A boolean version of [element].
 */
fun ClassSerialDescriptorBuilder.boolean(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Boolean>(elementName, annotations, isOptional)

/**
 * A byte version of [element].
 */
fun ClassSerialDescriptorBuilder.byte(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Byte>(elementName, annotations, isOptional)

/**
 * A short version of [element].
 */
fun ClassSerialDescriptorBuilder.short(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Short>(elementName, annotations, isOptional)

/**
 * An int version of [element].
 */
fun ClassSerialDescriptorBuilder.int(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Int>(elementName, annotations, isOptional)

/**
 * A long version of [element].
 */
fun ClassSerialDescriptorBuilder.long(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Long>(elementName, annotations, isOptional)

/**
 * A float version of [element].
 */
fun ClassSerialDescriptorBuilder.float(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Float>(elementName, annotations, isOptional)

/**
 * A double version of [element].
 */
fun ClassSerialDescriptorBuilder.double(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<Double>(elementName, annotations, isOptional)

/**
 * An enum version of [element].
 */
fun ClassSerialDescriptorBuilder.enum(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element<String>(elementName, annotations, isOptional)

/**
 * An uuid version of [element].
 */
fun ClassSerialDescriptorBuilder.uuid(
	elementName: String,
	annotations: List<Annotation> = emptyList(),
	isOptional: Boolean = false
) = element(elementName, UUIDSerializer.descriptor, annotations, isOptional)
