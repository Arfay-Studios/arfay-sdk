/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.chat

import net.arfay.sdk.strings.colored
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

class Confirmations (
    private var player: Player,
    private var message: String,
    private var acceptMessage: String,
    private var denyMessage: String,
    private var acceptHover: String,
    private var denyHover: String,
    private var whenAccept: String,
    private var whenDeny: String,
){
    fun send() {

        val text = TextComponent(message)

        val accept = TextComponent(acceptMessage)
        accept.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, whenAccept)
        accept.hoverEvent = HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            ComponentBuilder(acceptHover).create()
        )

        val deny = TextComponent(denyMessage)
        deny.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, whenDeny)
        deny.hoverEvent = HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            ComponentBuilder(denyHover).create()
        )

        text.addExtra(accept)
        text.addExtra("&r&f / ".colored())
        text.addExtra(deny)

        player.spigot().sendMessage(deny)
    }
}