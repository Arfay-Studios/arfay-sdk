@file:Suppress("NOTHING_TO_INLINE")

package arfay.graphical

import arfay.core.utils.*
import org.bukkit.inventory.*

/**
 * Creates a new empty engine.
 */
fun emptyEngine() = newEngine(Materials.AIR, 1)

/**
 * Creates a new engine by the specified type and amount.
 */
fun newEngine(type: Materials, amount: Int = 1) =
   Engine(type).apply {
      this.amount = amount
   }

/**
 * Creates a new engine by the specified type, name and amount.
 */
fun newEngine(type: Materials, name: String, amount: Int = 1) =
   newEngine(type, amount).apply {
      name(name)
   }

/**
 * Creates a new engine by the specified type, lore and amount.
 */
fun newEngine(type: Materials, lore: List<String>, amount: Int = 1) =
   newEngine(type, amount).apply {
      lore(lore)
   }

/**
 * Creates a new engine by the specified type, name, lore and amount.
 */
fun newEngine(type: Materials, name: String, lore: List<String>, amount: Int = 1) =
   newEngine(type, amount).apply {
      name(name)
      lore(lore)
   }

/**
 * Creates a new engine by the specified type and amount.
 */
fun newEngine(model: ItemStack, amount: Int = 1) =
   Engine(model).apply {
      this.amount = amount
   }

/**
 * Creates a new engine by the specified type, name and amount.
 */
fun newEngine(model: ItemStack, name: String, amount: Int = 1) =
   newEngine(model, amount).apply {
      name(name)
   }

/**
 * Creates a new engine by the specified type, lore and amount.
 */
fun newEngine(model: ItemStack, lore: List<String>, amount: Int = 1) =
   newEngine(model, amount).apply {
      lore(lore)
   }

/**
 * Creates a new engine by the specified type, name, lore and amount.
 */
fun newEngine(model: ItemStack, name: String, lore: List<String>, amount: Int = 1) =
   newEngine(model, amount).apply {
      name(name)
      lore(lore)
   }

/**
 * Builds a new engine by the specified type and amount.
 */
inline fun buildEngine(type: Materials, amount: Int = 1, builder: Engine.() -> Unit) =
   newEngine(type, amount).apply(builder)

/**
 * Builds a new engine by the specified type, name and amount.
 */
inline fun buildEngine(type: Materials, name: String, amount: Int = 1, builder: Engine.() -> Unit) =
   newEngine(type, amount).apply {
      name(name)
      builder()
   }

/**
 * Builds a new engine by the specified type, lore and amount.
 */
inline fun buildEngine(type: Materials, lore: List<String>, amount: Int = 1, builder: Engine.() -> Unit) =
   newEngine(type, amount).apply {
      lore(lore)
      builder()
   }

/**
 * Builds a new engine by the specified type, name, lore and amount.
 */
inline fun buildEngine(
   type: Materials,
   name: String,
   lore: List<String>,
   amount: Int = 1,
   builder: Engine.() -> Unit,
) = newEngine(type, amount).apply {
   name(name)
   lore(lore)
   builder()
}

/**
 * Creates a new engine by the specified type and amount.
 */
inline fun buildEngine(model: ItemStack, amount: Int = 1, builder: Engine.() -> Unit) =
   newEngine(model, amount).apply(builder)

/**
 * Creates a new engine by the specified type, name and amount.
 */
inline fun buildEngine(model: ItemStack, name: String, amount: Int = 1, builder: Engine.() -> Unit) =
   newEngine(model, amount).apply {
      name(name)
      builder()
   }

/**
 * Creates a new engine by the specified type, lore and amount.
 */
inline fun buildEngine(model: ItemStack, lore: List<String>, amount: Int = 1, builder: Engine.() -> Unit) =
   newEngine(model, amount).apply {
      lore(lore)
      builder()
   }

/**
 * Creates a new engine by the specified type, name, lore and amount.
 */
inline fun buildEngine(
   model: ItemStack,
   name: String,
   lore: List<String>,
   amount: Int = 1,
   builder: Engine.() -> Unit,
) = newEngine(model, amount).apply {
   name(name)
   lore(lore)
   builder()
}

/**
 * Builds the newly created empty engine with the specified [block].
 */
inline fun buildEngine(block: Engine.() -> Unit): Engine {
   return newEngine(Materials.AIR, 1).apply(block)
}
