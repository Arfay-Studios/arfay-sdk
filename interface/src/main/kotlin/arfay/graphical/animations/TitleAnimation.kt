package arfay.graphical.animations

import arfay.core.utils.*
import arfay.graphical.*
import com.soywiz.kds.random.*

fun <T> Array<T>.toIndexList() = IndexList(asIterable())

fun IGraphical.titleAnimation(vararg titles: String) {
   val opt = titles.toIndexList()
   addAnimation {
      clientTitle = opt.toNextOrFirst()
   }
}

fun IGraphical.randomTitleAnimation(vararg titles: String) {
   addAnimation {
      clientTitle = titles.fastRandom()
   }
}

fun IGraphical.titleAnimation(ticks: Int, vararg titles: String) {
   val opt = titles.toIndexList()
   addAnimation(ticks) {
      clientTitle = opt.toNextOrFirst()
   }
}

fun IGraphical.randomTitleAnimation(ticks: Int, vararg titles: String) {
   addAnimation(ticks) {
      clientTitle = titles.fastRandom()
   }
}
