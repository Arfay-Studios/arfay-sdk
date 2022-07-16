/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.chat

import java.awt.Color
import java.util.regex.Pattern

class ChatColor(
    val code: Char, name: String,
    private val color: Color? = null
) {

    companion object {

        val COLOR_CHAR = '\u00A7'
        val ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx"

        private val BY_CHAR: MutableMap<Char, arfay.sdk.chat.ChatColor> = HashMap()
        private val BY_NAME: MutableMap<String, arfay.sdk.chat.ChatColor> = HashMap()

        val STRIP_COLOR_PATTERN = Pattern.compile("(?i)${arfay.sdk.chat.ChatColor.Companion.COLOR_CHAR}[0-9A-FK-ORX]")

        val BLACK = arfay.sdk.chat.ChatColor('0', "black", Color(0x000000))
        val DARK_BLUE = arfay.sdk.chat.ChatColor('1', "dark_blue", Color(0x0000AA))
        val DARK_GREEN = arfay.sdk.chat.ChatColor('2', "dark_green", Color(0x00AA00))
        val DARK_AQUA = arfay.sdk.chat.ChatColor('3', "dark_aqua", Color(0x00AAAA))
        val DARK_RED = arfay.sdk.chat.ChatColor('4', "dark_red", Color(0xAA0000))
        val DARK_PURPLE = arfay.sdk.chat.ChatColor('5', "dark_purple", Color(0xAA00AA))
        val GOLD = arfay.sdk.chat.ChatColor('6', "gold", Color(0xFFAA00))
        val GRAY = arfay.sdk.chat.ChatColor('7', "gray", Color(0xAAAAAA))
        val DARK_GRAY = arfay.sdk.chat.ChatColor('8', "dark_gray", Color(0x555555))
        val BLUE = arfay.sdk.chat.ChatColor('9', "blue", Color(0x5555FF))
        val GREEN = arfay.sdk.chat.ChatColor('a', "green", Color(0x55FF55))
        val AQUA = arfay.sdk.chat.ChatColor('b', "aqua", Color(0x55FFFF))
        val RED = arfay.sdk.chat.ChatColor('c', "red", Color(0xFF5555))
        val LIGHT_PURPLE = arfay.sdk.chat.ChatColor('d', "light_purple", Color(0xFF55FF))
        val YELLOW = arfay.sdk.chat.ChatColor('e', "yellow", Color(0xFFFF55))
        val WHITE = arfay.sdk.chat.ChatColor('f', "white", Color(0xFFFFFF))
        val MAGIC = arfay.sdk.chat.ChatColor('k', "obfuscated")
        val BOLD = arfay.sdk.chat.ChatColor('l', "bold")
        val STRIKETHROUGH = arfay.sdk.chat.ChatColor('m', "strikethrough")
        val UNDERLINE = arfay.sdk.chat.ChatColor('n', "underline")
        val ITALIC = arfay.sdk.chat.ChatColor('o', "italic")
        val RESET = arfay.sdk.chat.ChatColor('r', "reset")

        fun stripColor(input: String?): String? {
            return if (input == null) {
                null
            } else arfay.sdk.chat.ChatColor.Companion.STRIP_COLOR_PATTERN.matcher(input).replaceAll("")
        }

        fun translateAlternateColorCodes(altColorChar: Char, textToTranslate: String): String? {
            val b = textToTranslate.toCharArray()
            for (i in 0 until b.size - 1) {
                if (b[i] == altColorChar && arfay.sdk.chat.ChatColor.Companion.ALL_CODES.indexOf(b[i + 1]) > -1) {
                    b[i] = arfay.sdk.chat.ChatColor.Companion.COLOR_CHAR
                    b[i + 1] = Character.toLowerCase(b[i + 1])
                }
            }
            return String(b)
        }

        fun getByChar(code: Char): arfay.sdk.chat.ChatColor? {
            return arfay.sdk.chat.ChatColor.Companion.BY_CHAR[code]
        }

        fun getLastColors(input: String): String {
            var result = ""

            val length = input.length

            for (index in length - 1 downTo -1 + 1) {
                val section = input[index]

                if (section == arfay.sdk.chat.ChatColor.Companion.COLOR_CHAR && index < length - 1) {
                    val c = input[index + 1]
                    val color = arfay.sdk.chat.ChatColor.Companion.getByChar(c)

                    if (color != null) {
                        result = color.toString() + result

                        if (color.isColor() || color == arfay.sdk.chat.ChatColor.Companion.RESET) {
                            break
                        }
                    }
                }
            }

            return result
        }

        fun fromHEX(hexCode: String): arfay.sdk.chat.ChatColor? {
            when (hexCode) {
                "#0000AA" -> return arfay.sdk.chat.ChatColor.Companion.DARK_BLUE
                "#00AA00" -> return arfay.sdk.chat.ChatColor.Companion.DARK_GREEN
                "#00AAAA" -> return arfay.sdk.chat.ChatColor.Companion.DARK_AQUA
                "#AA0000" -> return arfay.sdk.chat.ChatColor.Companion.DARK_RED
                "#AA00AA" -> return arfay.sdk.chat.ChatColor.Companion.DARK_PURPLE
                "#555555" -> return arfay.sdk.chat.ChatColor.Companion.DARK_GRAY
                "#FF55FF" -> return arfay.sdk.chat.ChatColor.Companion.LIGHT_PURPLE
                "#000000" -> return arfay.sdk.chat.ChatColor.Companion.BLACK
                "#FFFFFF" -> return arfay.sdk.chat.ChatColor.Companion.WHITE
                "#FFAA00" -> return arfay.sdk.chat.ChatColor.Companion.GOLD
                "#AAAAAA" -> return arfay.sdk.chat.ChatColor.Companion.GRAY
                "#5555FF" -> return arfay.sdk.chat.ChatColor.Companion.BLUE
                "#55FF55" -> return arfay.sdk.chat.ChatColor.Companion.GREEN
                "#55FFFF" -> return arfay.sdk.chat.ChatColor.Companion.AQUA
                "#FF5555" -> return arfay.sdk.chat.ChatColor.Companion.RED
                "#FFFF55" -> return arfay.sdk.chat.ChatColor.Companion.YELLOW
            }

            return null
        }

        public operator fun arfay.sdk.chat.ChatColor.plus(chatColor: arfay.sdk.chat.ChatColor) = this.toString() + chatColor.toString()

        public operator fun arfay.sdk.chat.ChatColor.plus(string: String) = this.toString() + string

        public operator fun arfay.sdk.chat.ChatColor.plus(char: Char) = this.toString() + char

    }

    private var count = 0

    private var ordinal = 0

    init {
        ordinal = count++
        arfay.sdk.chat.ChatColor.Companion.BY_CHAR[code] = this
        arfay.sdk.chat.ChatColor.Companion.BY_NAME[name.uppercase()] = this
    }

    fun isColor() = arrayOf(
       arfay.sdk.chat.ChatColor.Companion.DARK_BLUE,
       arfay.sdk.chat.ChatColor.Companion.DARK_AQUA,
       arfay.sdk.chat.ChatColor.Companion.DARK_GRAY,
       arfay.sdk.chat.ChatColor.Companion.DARK_GREEN,
       arfay.sdk.chat.ChatColor.Companion.DARK_PURPLE,
       arfay.sdk.chat.ChatColor.Companion.DARK_RED,
       arfay.sdk.chat.ChatColor.Companion.LIGHT_PURPLE,
       arfay.sdk.chat.ChatColor.Companion.BLACK,
       arfay.sdk.chat.ChatColor.Companion.WHITE,
       arfay.sdk.chat.ChatColor.Companion.RED,
       arfay.sdk.chat.ChatColor.Companion.GREEN,
       arfay.sdk.chat.ChatColor.Companion.YELLOW,
       arfay.sdk.chat.ChatColor.Companion.GOLD,
       arfay.sdk.chat.ChatColor.Companion.BLUE,
       arfay.sdk.chat.ChatColor.Companion.AQUA
    ).contains(this)

    override fun toString() = String(
        charArrayOf(
           arfay.sdk.chat.ChatColor.Companion.COLOR_CHAR, code
        )
    )

}
