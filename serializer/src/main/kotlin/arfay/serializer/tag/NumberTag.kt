package arfay.serializer.tag

interface NumberTag : Tag {
   fun toNumber(): Number
   fun toByte(): Byte
   fun toShort(): Short
   fun toInt(): Int
   fun toLong(): Long
   fun toFloat(): Float
   fun toDouble(): Double
   
   operator fun compareTo(value: Byte) = toByte().compareTo(value)
   operator fun compareTo(value: Short) = toShort().compareTo(value)
   operator fun compareTo(value: Int) = toInt().compareTo(value)
   operator fun compareTo(value: Long) = toLong().compareTo(value)
   operator fun compareTo(value: Float) = toFloat().compareTo(value)
   operator fun compareTo(value: Double) = toDouble().compareTo(value)
}
