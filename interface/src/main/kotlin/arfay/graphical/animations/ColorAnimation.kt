package arfay.graphical.animations

import arfay.core.utils.*
import arfay.graphical.*

internal val COLORS = Materials.filter { it.hasColorVariety }

fun IGraphical.colorAnimation() = backgroundAnimation(COLORS)
fun IGraphical.colorAnimation(ticks: Int) = backgroundAnimation(ticks, COLORS)

fun IGraphical.randomColorAnimation() = randomBackgroundAnimation(COLORS)
fun IGraphical.randomColorAnimation(ticks: Int) = randomBackgroundAnimation(ticks, COLORS)
