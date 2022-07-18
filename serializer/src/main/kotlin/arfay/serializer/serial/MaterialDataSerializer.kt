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
import kotlinx.serialization.encoding.*
import org.bukkit.*
import org.bukkit.material.*

/**
 * A serializer for MaterialData.
 *
 * This will serialize something like this (IN YAML)
 * A grass material example:
 * ```yaml
 * material: "1:0"
 * ```
 */
object MaterialDataSerializer : Serializer<MaterialData> {
   override val descriptor = StringDescriptor("MaterialData")
   
   override fun deserialize(decoder: Decoder): MaterialData {
      val split = decoder.decodeString().split(':', limit = 2)
      return MaterialData(Material.getMaterial(split[0].toInt()), split[1].toByte())
   }
   
   override fun serialize(encoder: Encoder, value: MaterialData) {
      encoder.encodeString("${value.itemType.id}:${value.data}")
   }
}
