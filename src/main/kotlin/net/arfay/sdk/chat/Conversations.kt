/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.chat

import net.arfay.sdk.extensions.msg
import org.bukkit.conversations.ConversationContext
import org.bukkit.conversations.ConversationFactory
import org.bukkit.conversations.Prompt
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

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
                    context.msg(fail.message)

                    return if (fail.stop) null else this
                }

                return null
            }
        }).buildConversation(this).begin()
    }
}