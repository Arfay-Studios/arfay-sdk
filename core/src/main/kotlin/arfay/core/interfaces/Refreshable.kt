package walkmc.interfaces

/**
 * Represents an object that can be refreshed/updated.
 */
interface Refreshable {
	
	/**
	 * Returns if it's allowing to refresh.
	 */
	var allowRefresh: Boolean
	
	/**
	 * Returns the interval of the refresh.
	 *
	 * Some implementations do' it in ticks or in milliseconds.
	 */
	var refreshInterval: Int
	
	/**
	 * Refreshs this refreshable object.
	 */
	fun refresh()
	
	/**
	 * Returns if this refreshable object can refresh.
	 */
	fun canRefresh(): Boolean = allowRefresh
}
