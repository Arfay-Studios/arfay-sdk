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
import arfay.serializer.tag.*
import dream.utils.*
import kotlinx.serialization.*
import kotlinx.serialization.encoding.*
import net.arfay.sdk.misc.*
import net.arfay.sdk.strings.*
import net.arfay.sdk.utils.*
import org.bukkit.inventory.*
import org.bukkit.inventory.meta.*

/**
 * A serializer for ItemStack.
 * This will serialize something like this (IN YAML)
 * ```yaml
 * item:
 *   name: "&eItem"
 *   material: "2:0"
 *   amount: 1
 *   glow: false
 *   lore:
 *     - "&7Hi! I'm a lore!"
 *   enchantments:
 *     - "DURABILITY(1)"
 * ```
 */
object ItemSerializer : KSerializer<ItemStack> {
   override val descriptor = descriptor("ItemStack") {
      element("material", MaterialDataSerializer.descriptor)
      string("head", isOptional = true)
      string("name")
      element("lore", StringListSerializer.descriptor)
      int("amount", isOptional = true)
      boolean("glow", isOptional = true)
      element("enchantments", EnchantmentSerializer.descriptor, isOptional = true)
   }
   
   override fun deserialize(decoder: Decoder): ItemStack {
      if (decoder is TagDecoder) {
         return decoder.input.readItem()
      }
      
      
      decoder.decode(descriptor) {
         val data = decode(MaterialDataSerializer)
         val head = decodeString()
         val name = decodeString()
         val lore = decode(StringListSerializer)
         val amount = decodeInt()
         val glow = decodeBoolean()
         val enchantments = decode(EnchantmentSerializer)
         
         val builder = ItemBuilder(data, amount)
            .name(name)
            .lore(lore)
            .enchantments(enchantments)
            .glowing(glow)
         
         if (head.isNotBlank()) {
            builder.item.material = Materials.PLAYER_SKULL
            builder.skull(head)
         }
         
         return builder.build()
      }
   }
   
   override fun serialize(encoder: Encoder, value: ItemStack) {
      if (encoder is TagEncoder) {
         encoder.output.writeItem(value)
         return
      }
      
      encoder.encode(descriptor) {
         val meta = value.itemMeta
         
         encode(value.data, MaterialDataSerializer)
         encodeString(if (meta is SkullMeta) meta.head else "")
         encodeString(meta?.displayName?.colored() ?: "")
         encode(meta?.lore ?: listOf(), StringListSerializer)
         encodeInt(value.amount)
         encodeBoolean(false)
         
         if (value.enchantments.isNotEmpty()) {
            encode(value.enchantments, EnchantmentSerializer)
         }
      }
   }
}
