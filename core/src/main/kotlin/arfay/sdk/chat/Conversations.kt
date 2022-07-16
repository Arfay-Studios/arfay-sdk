/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.chat

import arfay.sdk.extensions.*
import org.bukkit.conversations.*
import org.bukkit.entity.*
import org.bukkit.plugin.*

class Conversations(
    var plugin: Plugin
) {

    var factory = ConversationFactory(plugin)

    private class ConversationFail(override val message: String, val stop: Boolean) : RuntimeException()

    class ConversationScope(val input: String, private val context: ConversationContext) {
        fun msg(message: String) {
            context.msg(message)
        }

        fun fail(message: String): Nothing {
            throw ConversationFail(message, false)
        }

        fun stop(message: String): Nothing {
            throw ConversationFail(message, true)
        }
    }

    fun Player.ask(message: String, handle: ConversationScope.() -> Unit) {
        factory.withFirstPrompt(object : Prompt {
            override fun getPromptText(context: ConversationContext): String = message
            override fun blocksForInput(context: ConversationContext): Boolean = true

            override fun acceptInput(context: ConversationContext, input: String?): Prompt? {
                try {
                    handle(ConversationScope(input.orEmpty(), context))
                } catch (fail: ConversationFail) {
                    context.msg("&c${fail.message}")

                    return if (fail.stop) null else this
                }

                return null
            }
        }).buildConversation(this).begin()
    }
}
