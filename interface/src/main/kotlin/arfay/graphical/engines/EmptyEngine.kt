package arfay.graphical.engines

import arfay.core.utils.*
import arfay.graphical.*
import arfay.graphical.interfaces.*
import org.bukkit.*
import org.bukkit.event.inventory.*
import org.bukkit.inventory.*

object EmptyEngine : Engine(Materials.AIR) {
   override var graphical: IGraphical? = null; set(value) = Unit
   
   override var slot = 0; set(value) = Unit
   override var isVisible = false; set(value) = Unit
   
   override fun getType() = Material.AIR
   override fun setType(type: Material?) = Unit
   
   override fun getAmount() = 0
   override fun setAmount(amount: Int) = Unit
   
   override fun press(event: InventoryClickEvent) = Unit
   override fun render() = Unit
   override fun scroll() = Unit
   
   override fun onPress(action: PressAction) = false
   override fun onRender(action: RenderAction) = false
   override fun onScroll(action: ScrollAction) = false
   
   override fun notifyChange() = Unit
   
   override fun alter(engine: Engine) = this
   override fun alter(item: ItemStack) = this
   override fun alter(engine: Engine, action: AlterAction) = this
   override fun alter(item: ItemStack, action: ItemStack.() -> Unit) = this
}
