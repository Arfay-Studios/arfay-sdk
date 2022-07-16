/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.sdk.extensions

import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.plugin.Plugin

inline fun task(
    delayToRun: Long = 0,
    repeatDelay: Long = -1,
    plugin: Plugin,
    crossinline runnable: BukkitRunnable.() -> Unit
) = task(delayToRun, repeatDelay, false, plugin, runnable)

inline fun Plugin.task(
    delayToRun: Long = 0,
    repeatDelay: Long = -1,
    crossinline runnable: BukkitRunnable.() -> Unit
) = task(delayToRun, repeatDelay, this, runnable)

inline fun ArfayPlugin<*>.task(
    delayToRun: Long = 0,
    repeatDelay: Long = -1,
    crossinline runnable: BukkitRunnable.() -> Unit
) = plugin.task(delayToRun, repeatDelay, runnable)

inline fun taskAsync(
    delayToRun: Long = 0,
    repeatDelay: Long = -1,
    plugin: Plugin,
    crossinline runnable: BukkitRunnable.() -> Unit
) = task(delayToRun, repeatDelay, true, plugin, runnable)

inline fun Plugin.taskAsync(
    delayToRun: Long = 0,
    repeatDelay: Long = -1,
    crossinline runnable: BukkitRunnable.() -> Unit
) = taskAsync(delayToRun, repeatDelay, this, runnable)

inline fun ArfayPlugin<*>.taskAsync(
    delayToRun: Long = 0,
    repeatDelay: Long = -1,
    crossinline runnable: BukkitRunnable.() -> Unit
) = plugin.taskAsync(delayToRun, repeatDelay, runnable)

inline fun task(
    delayToRun: Long,
    repeatDelay: Long = -1,
    async: Boolean,
    plugin: Plugin,
    crossinline runnable: BukkitRunnable.() -> Unit
) = scheduler(runnable).run {
    if (repeatDelay > -1) if (async) runTaskTimerAsynchronously(plugin, delayToRun, repeatDelay) else runTaskTimer(plugin, delayToRun, repeatDelay)
    else if (delayToRun > 0) if (async) runTaskLaterAsynchronously(plugin, delayToRun) else runTaskLater(plugin, delayToRun)
    else if (async) runTaskAsynchronously(plugin) else runTask(plugin)
}

inline fun scheduler(crossinline runnable: BukkitRunnable.() -> Unit) = object : BukkitRunnable() {
    override fun run() {
        this.runnable()
    }
}
