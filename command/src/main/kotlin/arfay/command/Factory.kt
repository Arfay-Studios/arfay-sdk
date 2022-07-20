package arfay.command

internal fun createInstructor(
   name: String,
   vararg aliases: String = emptyArray(),
   sender: Sender = Sender.ALL,
   permission: String? = null,
   action: Instructor.() -> Unit,
) = Instructor(name, *aliases) {
   this.sender = sender
   if (permission != null) this.permission = permission
   action(this)
}

internal fun createInstructor(
   name: String,
   vararg aliases: String = emptyArray(),
   onlyPlayers: Boolean = false,
   permission: String? = null,
   action: Instructor.() -> Unit,
) = createInstructor(
   name,
   *aliases,
   sender = if (onlyPlayers) Sender.PLAYER else Sender.ALL,
   permission = permission,
   action = action
)

internal fun createInstructorChildren(
   parent: Instructor,
   name: String,
   vararg aliases: String = emptyArray(),
   sender: Sender = Sender.ALL,
   permission: String? = null,
   action: Instructor.() -> Unit,
) = ChildrenInstructor(parent, name, *aliases) {
   this.sender = sender
   this.permission = permission ?: parent.permission
   action(this)
}

internal fun createInstructorChildren(
   parent: Instructor,
   name: String,
   vararg aliases: String = emptyArray(),
   onlyPlayers: Boolean = false,
   permission: String? = null,
   action: Instructor.() -> Unit,
) = createInstructorChildren(
   parent,
   name,
   *aliases,
   sender = if (onlyPlayers) Sender.PLAYER else Sender.ALL,
   permission = permission,
   action = action
)

/**
 * Creates a new main instructor with specified name, aliases and builder action.
 */
fun ComplexCommand(
   name: String,
   vararg aliases: String = emptyArray(),
   onlyPlayers: Boolean = false,
   permission: String? = null,
   action: Instructor.() -> Unit,
): Instructor {
   val command = createInstructor(name, *aliases, onlyPlayers = onlyPlayers, permission = permission, action = action)
   command.register()
   return command
}

/**
 * Creates a new main instructor with specified
 * name, aliases and action as performer of this instructor.
 */
fun Command(
   name: String,
   vararg aliases: String = emptyArray(),
   onlyPlayers: Boolean = false,
   permission: String? = null,
   action: Performer.(Instructor) -> Unit,
): Instructor {
   val command = createInstructor(name, *aliases, onlyPlayers = onlyPlayers, permission = permission) {
      performs(action)
   }
   
   command.register()
   return command
}
