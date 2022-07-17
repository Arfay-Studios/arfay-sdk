/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.core.chat

import arfay.core.extensions.*
import arfay.core.utils.msg
import net.md_5.bungee.api.chat.*
import org.bukkit.entity.*

data class Confirmations(
   var player: Player,
   var message: String,
   var acceptMessage: String,
   var denyMessage: String,
   var separator: String,
   var acceptHover: String,
   var denyHover: String,
   var whenAccept: String,
   var whenDeny: String,
) {
   
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
