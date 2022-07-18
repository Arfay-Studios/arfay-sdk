package arfay.graphical.animations

import arfay.core.utils.*
import walkmc.*
import walkmc.graphical.*

internal val GLASSES = Materials.filter { it.isPaneGlass }

fun IGraphical.glassAnimation() = backgroundAnimation(GLASSES)
fun IGraphical.glassAnimation(ticks: Int) = backgroundAnimation(ticks, GLASSES)

fun IGraphical.randomGlassAnimation() = randomBackgroundAnimation(GLASSES)
fun IGraphical.randomGlassAnimation(ticks: Int) = randomBackgroundAnimation(ticks, GLASSES)

