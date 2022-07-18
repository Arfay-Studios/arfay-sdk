package walkmc.interfaces

import walkmc.extensions.*

/**
 * A tickable interface represents an object that can tick in the server.
 *
 * You must register the implementation to be able to server recognize them with [register].
 */
interface Tickable {
	
	/**
	 * The amount of ticks elapsed.
	 *
	 * You can use this for `delaying` the tickable implementation
	 */
	var ticks: Int
	
	/**
	 * Tick execution.
	 */
	fun onTick()
	
	/**
	 * Starts the executions of this tickable.
	 */
	fun register() = server.registerTickable(this)
	
	/**
	 * Stops the execution of this tickable.
	 *
	 * One time stopped, the execution cannot be done until calling [register].
	 */
	fun unregister() = server.unregisterTickable(this)
}
