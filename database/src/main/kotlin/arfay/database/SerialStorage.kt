/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.database

import kotlinx.serialization.Serializable
import net.arfay.sdk.database.factory.PrototypeFactory
import java.io.File

/**
 * A serial prototype is a serializable class thats supports
 * to configure the database prototype settings.
 */
@Serializable
data class SerialStorage(
    var type: DatabaseType = DatabaseType.SQLITE,
    var username: String = "root",
    var password: String = "",
    var host: String = "localhost",
    var database: String = "",
    var port: Int = 3306,
    var useSSL: Boolean = false,
)

/**
 * Converts this [SerialStorage] to a [LocalStorage],
 * or throws a exception, if the type of this prototype is not a
 * local database prototype.
 */
fun SerialStorage.createForLocal(file: File): LocalStorage {
	require(type == DatabaseType.SQLITE) {
		"The database type $type provided is not a local database prototype. Only 'SQLITE' is local."
	}
	return SQLite(file)
}

/**
 * Converts this [SerialStorage] to a [LocalStorage],
 * or throws a exception, if the type of this prototype is not a
 * local database prototype.
 */
fun SerialStorage.createForServer(): ServerStorage {
	require(type == DatabaseType.MYSQL) {
		"The database type $type provided is not a server database prototype. Only 'MYSQL' and 'POSTGRE' is server."
	}
	return PrototypeFactory.createMySQLPrototype(this)
}

/**
 * Converts this [SerialStorage] to a [Storage]. This is
 * used when the database prototype can be local or server.
 * So this is a abstraction for all supported types.
 *
 * A [file] can be required if
 * the prototype is a [LocalStorage], by default is null.
 */
fun SerialStorage.create(file: File? = null): Storage {
	return when (type) {
		DatabaseType.SQLITE -> {
			requireNotNull(file) { "The database type $type is local, but no file is specified." }
			PrototypeFactory.createSQLitePrototype(file)
		}
		DatabaseType.MYSQL -> PrototypeFactory.createMySQLPrototype(this)
		else -> error("No database type found with type $type")
	}
}
