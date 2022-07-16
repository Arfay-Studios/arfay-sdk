/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.database.factory

import net.arfay.sdk.database.*
import net.arfay.sdk.database.SerialStorage
import java.io.*

/**
 * A factory object used to create new databases prototypes.
 */
object PrototypeFactory {
	
	/**
	 * Creates a new [SQLite] by the specified file.
	 */
	fun createSQLitePrototype(file: File): LocalStorage = SQLite(file)
	
	/**
	 * Creates a new [MySQL] by the specifieds parameters
	 * to connect to the server database.
	 */
	fun createMySQLPrototype(
		username: String,
		password: String,
		databaseName: String,
		host: String,
		port: Int = 3306,
		useSSL: Boolean = false,
	): ServerStorage = MySQL(username, password, databaseName, host, port, useSSL)
	
	/**
	 * Creates a new [MySQL] by the serial prototype
	 * parameters to connect to the server database.
	 */
	fun createMySQLPrototype(serial: SerialStorage): ServerStorage {
		return MySQL(
			serial.username,
			serial.password,
			serial.database,
			serial.host,
			serial.port,
			serial.useSSL
		)
	}
}
