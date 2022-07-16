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

package arfay.serializer.serial

import arfay.serializer.common.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import net.arfay.sdk.strings.*

/**
 * A serializer for String Lists. By default
 * kotlin serialization already adds supports for
 * lists serializers. So this is just a "strategy" to
 * encode and decode color symbols, like '&' to '§' and vice-versa
 */
object StringListSerializer : KSerializer<List<String>> {
	override val descriptor = listSerialDescriptor<String>()
	
	override fun deserialize(decoder: Decoder) = decoder.decode(descriptor) {
		val list = ArrayList<String>()
		
		while (true) {
			when (val index = decodeIndex()) {
				CompositeDecoder.DECODE_DONE -> break
				else -> {
					val value = decodeString(index)
					list.add(value.reverseColorize())
				}
			}
		}
		
		list
	}
	
	override fun serialize(encoder: Encoder, value: List<String>) = encoder.encode(descriptor) {
		for (str in value) {
			encodeString(str)
		}
	}
}
