package arfay.core.utils

import net.minecraft.server.*
import net.minecraft.server.v1_8_R3.*
import org.bukkit.*
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
import org.bukkit.entity.*

/**
 * An abstract pathfinder implementation to create new pathfinders goals.
 */
abstract class Behavior(val owner: EntityInsentient) : PathfinderGoal() {
	
	/**
	 * Returns the bukkit entity owner.
	 */
	val bukkitOwner: LivingEntity
		get() = owner.bukkitEntity as CraftLivingEntity
	
	/**
	 * Returns the world of the [owner].
	 */
	val world: WorldServer
		get() = owner.world as WorldServer
	
	/**
	 * Returns the current location of [owner].
	 */
	val location: Location
		get() = bukkitOwner.location
	
	/**
	 * Returns the current location X of [owner].
	 */
	val posX: Double
		get() = owner.locX
	
	/**
	 * Returns the current location Y of [owner].
	 */
	val posY: Double
		get() = owner.locY
	
	/**
	 * Returns the current location Z of [owner].
	 */
	val posZ: Double
		get() = owner.locZ
	
	/**
	 * Returns the navigation of the [owner].
	 */
	val navigation: NavigationAbstract
		get() = owner.navigation
	
	/**
	 * Returns whether the Behavior should begin execution.
	 */
	abstract fun shouldExecute(): Boolean
	
	/**
	 * Returns whether an in-progress Behavior should continue executing
	 */
	open fun canContinueExecuting(): Boolean {
		return shouldExecute()
	}
	
	/**
	 * Determine if this AI Task is interruptible by a higher (= lower value) priority task.
	 * All vanilla AITask have this value set to true.
	 */
	open fun isInterruptible(): Boolean {
		return true
	}
	
	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	open fun startExecuting() {
	}
	
	/**
	 * Resets the task
	 */
	open fun resetTask() {
	}
	
	/**
	 * Updates the task
	 */
	open fun updateTask() {
	}
	
	override fun a(): Boolean = shouldExecute()
	override fun b(): Boolean = canContinueExecuting()
	override fun i(): Boolean = isInterruptible()
	override fun c() = startExecuting()
	override fun d() = resetTask()
	override fun e() = updateTask()
}
