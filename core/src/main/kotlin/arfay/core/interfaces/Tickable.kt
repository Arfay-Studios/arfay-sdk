package arfay.core.interfaces

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

}
