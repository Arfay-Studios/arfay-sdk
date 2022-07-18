package arfay.graphical.engines

import arfay.core.utils.*
import com.soywiz.korma.math.*
import org.bukkit.event.inventory.*
import org.bukkit.inventory.*
import walkmc.*
import walkmc.graphical.*

/**
 * An abstract implementation of [Engine] that's holds a counter.
 */
open class CountEngine : Engine {
   constructor(type: Materials, amount: Int = 1) : super(type, amount)
   constructor(stack: ItemStack) : super(stack)
   
   open var count by int(1) { handleChange(it) }
   open var range = Int.MIN_VALUE..Int.MAX_VALUE
   open var inc = 1
   open var dec = 1
   open var isCountEnabled = true
   open var allowIncrease = true
   open var allowDecrease = false
   
   open var changeCallback: (CountEngine.(Int) -> Unit)? = null
   
   /**
    * Sets the count change callback.
    */
   fun onCountChange(callback: CountEngine.(Int) -> Unit) {
      changeCallback = callback
   }
   
   /**
    * Handles any change in the counter.
    */
   open fun handleChange(value: Int) {
      changeCallback?.invoke(this, value)
      notifyChange()
   }
   
   override fun handleClick(event: InventoryClickEvent) {
      if (isCountEnabled) {
         count = when {
            event.isRightClick && allowDecrease -> (count - dec).clamp(range.first, range.last)
            event.isLeftClick && allowIncrease -> (count + inc).clamp(range.first, range.last)
            else -> return
         }
      }
   }
}

/**
 * An implementation of [CountEngine] that's the counter of the
 * engine changes the stack amount in the graphical.
 */
open class AmountCountEngine : CountEngine {
   constructor(type: Materials, amount: Int = 1) : super(type, amount)
   constructor(stack: ItemStack) : super(stack)
   
   override var range = 1..material.maxStack
   
   override fun handleChange(value: Int) {
      amount = value
      super.handleChange(value)
   }
}
