/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.chat

import net.md_5.bungee.api.chat.*
import org.bukkit.entity.Player

inline fun <T> pagination(source: Iterable<T>, builder: ChatPagination<T>.() -> Unit): ChatPagination<T> {
    val pagination = ChatPagination(source).apply(builder)
    pagination.make()

    return pagination
}

typealias IndexRequestor<T> = ((T, Int) -> BaseComponent)?
typealias ChatPageList = List<List<BaseComponent>>

class ChatPagination<T>(var source: Iterable<T>) {

    var backText = "§c§l[Voltar Página]"

    var nextText = " §e§l[Avançar Página]"

    var backHover = "§7Clique para voltar de página."

    var nextHover = "§7Clique para avançar de página."

    var command = "/page"

    /** The requestor used to map [T] values in a chat component.*/
    var requestor: IndexRequestor<T> = null

    /** In how much [source] will be chunked. */
    var chunkBy = 11

    /** The cached pages of this pagination. */
    var pages: ChatPageList = ArrayList()

    /** Changes the requestor index. */
    fun requestIndex(request: IndexRequestor<T>) {
        requestor = request
    }

    /** Adds all requested [source] on [pages]. */
    fun make() {
        val mapped = source.mapIndexed { index, value -> requestor!!(value, index) }

        pages = mapped.chunked(chunkBy)
    }

    fun show(player: Player, page: Int) {
        repeat(10) { player.sendMessage("§0\n") }

        pages[page - 1].forEach { player.spigot().sendMessage(it) }

        val previous = TextComponent(backText)
            .run("$command " + (page - 1))
            .show(backHover)

        val next = TextComponent(nextText)
            .run("$command " + (page + 1))
            .show(nextHover)

        when (page) {
            1 -> player.spigot().sendMessage(next)
            pages.size -> player.spigot().sendMessage(previous)
            else -> player.spigot().sendMessage(previous, next)
        }
    }
}
