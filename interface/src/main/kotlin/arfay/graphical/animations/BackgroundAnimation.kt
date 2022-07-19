package arfay.graphical.animations

import arfay.core.utils.*
import arfay.graphical.*
import com.soywiz.kds.random.*

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
