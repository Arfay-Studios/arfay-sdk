/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.chat

import net.md_5.bungee.api.chat.*
import net.minecraft.server.v1_8_R3.IChatBaseComponent

/**
 * Creates a minecraft chat component by the specified [text].
 */
fun createChatComponent(text: String): IChatBaseComponent =
    IChatBaseComponent.ChatSerializer.a("{\"text\":\"$text\"}")

/**
 * Transforms this text component to shows the specified text when hover.
 */
fun TextComponent.show(text: String) = apply {
    hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(text).create())
}

/**
 * Transforms this text component to runs the specified text when click.
 */
fun TextComponent.run(text: String) = apply {
    clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, text)
}

/**
 * Transforms this text component to suggests the specified text when click.
 */
fun TextComponent.suggest(text: String) = apply {
    clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, text)
}

/**
 * Transforms this text component to opens a url with the specified text when click.
 */
fun TextComponent.url(text: String) = apply {
    clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, text)
}

/**
 * Transforms this text component to appends a new text.
 */
fun TextComponent.append(text: String) = apply {
    addExtra(text)
}

/**
 * Transforms this text component to appends a new text.
 */
fun TextComponent.append(text: BaseComponent) = apply {
    addExtra(text)
}

/**
 * Adds the specified [text] to this component
 */
operator fun TextComponent.plusAssign(text: String) {
    addExtra(text)
}

/**
 * Adds the specified [text] to this component
 */
operator fun TextComponent.plusAssign(text: BaseComponent) {
    addExtra(text)
}
