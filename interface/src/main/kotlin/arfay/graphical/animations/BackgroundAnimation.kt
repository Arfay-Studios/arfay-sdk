package arfay.graphical.animations

import arfay.core.utils.*
import com.soywiz.kds.random.*
import walkmc.graphical.*

fun IGraphical.backgroundAnimation(items: List<Materials>) {
   val result = items.toIndexList()
   addAnimation {
      background = result.toNextOrFirst()
   }
}

fun IGraphical.backgroundAnimation(ticks: Int, items: List<Materials>) {
   val result = items.toIndexList()
   addAnimation(ticks) {
      background = result.toNextOrFirst()
   }
}

fun IGraphical.randomBackgroundAnimation(items: List<Materials>) {
   addAnimation {
      background = items.fastRandom()
   }
}

fun IGraphical.randomBackgroundAnimation(ticks: Int, items: List<Materials>) {
   addAnimation(ticks) {
      background = items.fastRandom()
   }
}
