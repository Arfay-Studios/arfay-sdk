/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.database

import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import kotlin.coroutines.*

typealias Management<T> = Transaction.() -> T

/**
 * A database scope is a coroutine scope thats executes
 * database transactions asynchronously.
 */
class DatabaseScope : CoroutineScope {
	override val coroutineContext: CoroutineContext = EmptyCoroutineContext
	
	/**
	 * Suspend function to manage database transactions in asynchronously.
	 */
	suspend fun <T> manage(database: Database, action: Management<T>): T {
		return withContext(Dispatchers.Default) {
			transaction(database, action)
		}
	}
	
	/**
	 * Manages a [manage] in launch coroutine scope.
	 */
	fun <T> management(database: Database, action: Management<T>): Job {
		return launch {
			manage(database, action)
		}
	}
	
	/**
	 * Manages a [manage] in launch coroutine scope with start as lazy.
	 */
	fun <T> lazyManagement(database: Database, action: Management<T>): Job {
		return launch(start = CoroutineStart.LAZY) {
			manage(database, action)
		}
	}
	
	/**
	 * Manages a [manage] in async coroutine scope.
	 */
	fun <T> managementAsync(database: Database, action: Management<T>): Deferred<T> {
		return async {
			manage(database, action)
		}
	}
	
	/**
	 * Manages a [manage] in async coroutine scope with start as lazy.
	 */
	fun <T> lazyManagementAsync(database: Database, action: Management<T>): Deferred<T> {
		return async(start = CoroutineStart.LAZY) {
			manage(database, action)
		}
	}
}
