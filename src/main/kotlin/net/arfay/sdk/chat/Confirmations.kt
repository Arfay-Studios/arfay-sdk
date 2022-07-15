/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.chat

import net.arfay.sdk.extensions.msg
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

class Confirmations (
    private var player: Player,
    private var message: String,
    private var acceptMessage: String,
    private var denyMessage: String,
    private var separator: String,
    private var acceptHover: String,
    private var denyHover: String,
    private var whenAccept: String,
    private var whenDeny: String,
){
    fun send() {
        player.msg(
            TextComponent(message)
                .append(acceptMessage)
                .run(whenAccept)
                .show(acceptHover)
                .append(separator)
                .append(denyMessage)
                .run(whenDeny)
                .show(denyHover)
        )
    }
}