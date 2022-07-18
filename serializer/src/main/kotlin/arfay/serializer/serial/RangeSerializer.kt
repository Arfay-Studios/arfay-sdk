package arfay.serializer.serial

import arfay.core.utils.*
import arfay.serializer.common.*
import kotlinx.serialization.*
import kotlinx.serialization.encoding.*

/**
 * A serializer of an int range.
 */
object IntRangeSerializer : KSerializer<IntRange> {
   override val descriptor = StringDescriptor("SerialRangeIntRange")
   
   override fun deserialize(decoder: Decoder): IntRange = decoder.decodeStructure(descriptor) {
      val split = decodeStringElement(descriptor, decodeElementIndex(descriptor)).split("..", limit = 2)
      split[0].toInt()..split[1].toInt()
   }
   
   override fun serialize(encoder: Encoder, value: IntRange) = encoder.encodeString(value.toString())
}

/**
 * A serializer of a long range.
 */
object LongRangeSerializer : KSerializer<LongRange> {
   override val descriptor = StringDescriptor("SerialRangeLongRange")
   
   override fun deserialize(decoder: Decoder): LongRange = decoder.decode(descriptor) {
      val split = decodeString().split("..", limit = 2)
      split[0].toLong()..split[1].toLong()
   }
   
   override fun serialize(encoder: Encoder, value: LongRange) = encoder.encodeString(value.toString())
}

/**
 * A serializer of a float range.
 */
object FloatRangeSerializer : KSerializer<FloatRange> {
   override val descriptor = StringDescriptor("SerialRangeFloatRange")
   
   override fun deserialize(decoder: Decoder): FloatRange = decoder.decode(descriptor) {
      val split = decodeString().split("..", limit = 2)
      split[0].toFloat()..split[1].toFloat()
   }
   
   override fun serialize(encoder: Encoder, value: FloatRange) = encoder.encodeString(value.toString())
}

/**
 * A serializer of a float range.
 */
object DoubleRangeSerializer : KSerializer<DoubleRange> {
   override val descriptor = StringDescriptor("SerialRangeDoubleRange")
   
   override fun deserialize(decoder: Decoder): DoubleRange = decoder.decode(descriptor) {
      val split = decodeString().split("..", limit = 2)
      split[0].toDouble()..split[1].toDouble()
   }
   
   override fun serialize(encoder: Encoder, value: DoubleRange) = encoder.encodeString(value.toString())
}
