package walkmc.interfaces

/**
 * Represents an object that has a name.
 */
interface Nameable {
	
	/**
	 * The current name of this object.
	 */
	var name: String
	
	/**
	 * Do a request to verify if this nameable object has a name.
	 *
	 * By default, verify if the [name] is not empty.
	 */
	fun hasName() = name.isNotEmpty()
}
