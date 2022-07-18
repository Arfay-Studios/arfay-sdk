@file:Suppress("NOTHING_TO_INLINE")

package arfay.core.utils

import kotlinx.serialization.*
import org.bukkit.*
import org.bukkit.entity.*
import org.bukkit.inventory.*
import org.bukkit.inventory.meta.*

/**
 * A Slot represents a storable index and item for uses with inventory.
 * This contains the index of the slot and the item of the slot.
 */
@Serializable
data class Slot(var slot: Int, var item: @Contextual ItemStack)

/**
 * Gives this slot representation to the specified inventory.
 */
inline fun Slot.add(inventory: Inventory) = inventory.setItem(slot, item)

/**
 * Gives this slot representation to the specified player.
 */
inline fun Slot.add(player: Player) = player.inventory.setItem(slot, item)

/**
 * Copies this slot and applies the [block] function to this.
 */
inline fun Slot.copy(block: Slot.() -> Unit) = copy().apply(block)

/**
 * Creates a [Slot] by this item and the specified [slot].
 */
inline infix fun ItemStack.slot(slot: Int) = Slot(slot, this)

/**
 * Changes this item stack material type by the specified [material].
 */
fun Slot.changeToMaterial(material: Materials): Slot {
	item.material = material
	return this
}

/**
 * Changes this item stack to the specified head representation of the owner [name].
 */
fun Slot.changeToHeadOwner(name: String): Slot {
	item.material = Materials.PLAYER_SKULL
	item.meta<SkullMeta> {
		this.owner = name
	}
	return this
}

/**
 * Changes this item stack to the specified head representation of the owner [player].
 */
fun Slot.changeToHeadOwner(player: OfflinePlayer): Slot {
	item.material = Materials.PLAYER_SKULL
	item.meta<SkullMeta> {
		this.owner = player.name
	}
	return this
}

/**
 * Copies and changes this item stack material type by the specified [material].
 */
fun Slot.copyToMaterial(material: Materials) = copy {
	item.material = material
}

/**
 * Copies and changes this item stack to the specified head representation of the owner [name].
 */
fun Slot.copyToHeadOwner(name: String) = copy {
	item.material = Materials.PLAYER_SKULL
	item.meta<SkullMeta> {
		owner = name
	}
}

/**
 * Copies and changes this item stack to the specified head representation of the owner [player].
 */
fun Slot.copyToHeadOwner(player: OfflinePlayer) = copy {
	item.material = Materials.PLAYER_SKULL
	item.meta<SkullMeta> {
		owner = player.name
	}
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore.
 */
fun Slot.process(model: ItemStack, value: Pair<String, Any>) = apply {
	item.process(model, value)
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore.
 */
fun Slot.process(model: ItemStack, vararg value: Pair<String, Any>) = apply {
	item.process(model, *value)
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore.
 */
fun Slot.process(model: ItemStack, value: Map<String, Any>) = apply {
	item.process(model, value)
}

/**
 * Process only specified placeholder in this item stack.
 */
fun Slot.process(value: Pair<String, Any>) = apply {
	item.process(value)
}

/**
 * Process all specifieds placeholders in this item stack.
 */
fun Slot.process(vararg values: Pair<String, Any>) = apply {
	item.process(*values)
}

/**
 * Process all specifieds placeholders in this item stack.
 */
fun Slot.process(values: Map<String, Any>) = apply {
	item.process(values)
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore and returns a copy of them.
 */
fun Slot.processCopy(model: ItemStack, value: Pair<String, Any>) = copy {
	item.process(model, value)
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore and returns a copy of them.
 */
fun Slot.processCopy(model: ItemStack, vararg value: Pair<String, Any>) = copy {
	item.process(model, *value)
}

/**
 * Process this item stack with the specified placeholder and value
 * by the model name and lore and returns a copy of them.
 */
fun Slot.processCopy(model: ItemStack, value: Map<String, Any>) = copy {
	item.process(model, value)
}

/**
 * Process only specified placeholder in this item stack and returns a copy of them.
 */
fun Slot.processCopy(value: Pair<String, Any>) = copy {
	item.process(value)
}

/**
 * Process all specifieds placeholders in this item stack and returns a copy of them.
 */
fun Slot.processCopy(vararg values: Pair<String, Any>) = copy {
	item.process(*values)
}

/**
 * Process all specifieds placeholders in this item stack and returns a copy of them.
 */
fun Slot.processCopy(values: Map<String, Any>) = copy {
	item.process(values)
}
