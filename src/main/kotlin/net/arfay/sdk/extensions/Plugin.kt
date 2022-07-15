/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.extensions

import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

fun Plugin.registerEvents(
    vararg listeners: Listener
) = listeners.forEach { server.pluginManager.registerEvents(it, this) }

fun ArfayPlugin<*>.registerEvents(
    vararg listeners: Listener
) = plugin.registerEvents(*listeners)

// logger
fun Plugin.info(message: String) = logger.info(message)
fun Plugin.warn(message: String) = logger.warning(message)
fun Plugin.severe(message: String) = logger.severe(message)
fun Plugin.debug(message: String) = logger.config(message)
fun Plugin.fine(message: String) = logger.fine(message)

fun ArfayPlugin<*>.info(message: String) = plugin.info(message)
fun ArfayPlugin<*>.warn(message: String) = plugin.warn(message)
fun ArfayPlugin<*>.severe(message: String) = plugin.severe(message)
fun ArfayPlugin<*>.debug(message: String) = plugin.debug(message)
fun ArfayPlugin<*>.fine(message: String) = plugin.fine(message)

interface ArfayPlugin<T : Plugin> { val plugin: T }