@file:Suppress("NOTHING_TO_INLINE")

package arfay.serializer.tag

import arfay.serializer.tag.types.*

inline fun Byte.toTag() = ByteTag.of(this)
inline fun Boolean.toTag() = ByteTag.of(this)
inline fun Short.toTag() = ShortTag.of(this)
inline fun Int.toTag() = IntTag.of(this)
inline fun Long.toTag() = LongTag.of(this)
inline fun Float.toTag() = FloatTag.of(this)
inline fun Double.toTag() = DoubleTag.of(this)
inline fun String.toTag() = StringTag.of(this)
inline fun ByteArray.toTag() = ByteArrayTag(this)
inline fun IntArray.toTag() = IntArrayTag(this)

fun CollectionTag<ByteTag>.getByte(index: Int) = get(index).value
fun CollectionTag<ShortTag>.getShort(index: Int) = get(index).value
fun CollectionTag<IntTag>.getInt(index: Int) = get(index).value
fun CollectionTag<LongTag>.getLong(index: Int) = get(index).value
fun CollectionTag<FloatTag>.getFloat(index: Int) = get(index).value
fun CollectionTag<DoubleTag>.getDouble(index: Int) = get(index).value
fun CollectionTag<StringTag>.getString(index: Int) = get(index).value
fun CollectionTag<ByteArrayTag>.getArray(index: Int) = get(index).value
fun CollectionTag<IntArrayTag>.getArray(index: Int) = get(index).value

fun CollectionTag<ByteTag>.add(value: Byte) = add(value.toTag())
fun CollectionTag<ShortTag>.add(value: Short) = add(value.toTag())
fun CollectionTag<IntTag>.add(value: Int) = add(value.toTag())
fun CollectionTag<LongTag>.add(value: Long) = add(value.toTag())
fun CollectionTag<FloatTag>.add(value: Float) = add(value.toTag())
fun CollectionTag<DoubleTag>.add(value: Double) = add(value.toTag())
fun CollectionTag<StringTag>.add(value: String) = add(value.toTag())
fun CollectionTag<ByteArrayTag>.add(value: ByteArray) = add(value.toTag())
fun CollectionTag<IntArrayTag>.add(value: IntArray) = add(value.toTag())

@JvmName("wrapByte")
fun CollectionTag<ByteTag>.wrap() = map { it.value }
@JvmName("wrapShort")
fun CollectionTag<ShortTag>.wrap() = map { it.value }
@JvmName("wrapInt")
fun CollectionTag<IntTag>.wrap() = map { it.value }
@JvmName("wrapLong")
fun CollectionTag<LongTag>.wrap() = map { it.value }
@JvmName("wrapFloat")
fun CollectionTag<FloatTag>.wrap() = map { it.value }
@JvmName("wrapDouble")
fun CollectionTag<DoubleTag>.wrap() = map { it.value }
@JvmName("wrapString")
fun CollectionTag<StringTag>.wrap() = map { it.value }
@JvmName("wrapByteArray")
fun CollectionTag<ByteArrayTag>.wrap() = map { it.value }
@JvmName("wrapIntArray")
fun CollectionTag<IntArrayTag>.wrap() = map { it.value }

@JvmName("wrapMutableByte")
fun CollectionTag<ByteTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableShort")
fun CollectionTag<ShortTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableInt")
fun CollectionTag<IntTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableLong")
fun CollectionTag<LongTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableFloat")
fun CollectionTag<FloatTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableDouble")
fun CollectionTag<DoubleTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableString")
fun CollectionTag<StringTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableByteArray")
fun CollectionTag<ByteArrayTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }
@JvmName("wrapMutableIntArray")
fun CollectionTag<IntArrayTag>.mutableWrap() = mapTo(ArrayList(size)) { it.value }

@JvmName("toByteTag")
fun Iterable<Byte>.toTag() = mapTo(ListTag()) { it.toTag() }
@JvmName("toShortTag")
fun Iterable<Short>.toTag() = mapTo(ListTag()) { it.toTag() }
@JvmName("toIntTag")
fun Iterable<Int>.toTag() = mapTo(ListTag()) { it.toTag() }
@JvmName("toLongTag")
fun Iterable<Long>.toTag() = mapTo(ListTag()) { it.toTag() }
@JvmName("toFloatTag")
fun Iterable<Float>.toTag() = mapTo(ListTag()) { it.toTag() }
@JvmName("toDoubleTag")
fun Iterable<Double>.toTag() = mapTo(ListTag()) { it.toTag() }
@JvmName("toStringTag")
fun Iterable<String>.toTag() = mapTo(ListTag()) { it.toTag() }

