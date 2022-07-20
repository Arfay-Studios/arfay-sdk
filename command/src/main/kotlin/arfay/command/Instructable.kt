package arfay.command

/**
 * A instructable object thats can instruct a command to execute.
 */
interface Instructable {
	
	/**
	 * The current performer action of this instructable object.
	 */
	var performer: Performer.(Instructor) -> Unit
	
	/**
	 * Sets the new performer action of this instructable object.
	 */
	fun performs(action: Performer.(Instructor) -> Unit) {
		performer = action
	}
	
	/**
	 * Returns if the type of sender thats can perform this instructable.
	 */
	var sender: Sender
	
	/**
	 * The alternates sub commands of this instructable.
	 */
	var childrens: MutableSet<ChildrenInstructor>
}

/**
 * Creates a alternate instructor of this instructable
 */
fun Instructor.complex(
	name: String,
	vararg aliases: String = emptyArray(),
	onlyPlayers: Boolean = false,
	permission: String? = null,
	action: Instructor.() -> Unit
): ChildrenInstructor = createChildren(
	name,
	*aliases,
	onlyPlayers = onlyPlayers,
	permission = permission,
	action = action
)

/**
 * Creates a alternate instructor with the specified
 * [action] as a performer of this instructable
 */
fun Instructor.sub(
	name: String,
	vararg aliases: String = emptyArray(),
	onlyPlayers: Boolean = false,
	permission: String? = null,
	action: Performer.(Instructor) -> Unit
): ChildrenInstructor = createChildren(name, *aliases, onlyPlayers = onlyPlayers, permission = permission) {
	performs(action)
}
