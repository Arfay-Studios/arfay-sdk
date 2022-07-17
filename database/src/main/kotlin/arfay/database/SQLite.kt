/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.database

import arfay.database.factory.configurationOf
import com.zaxxer.hikari.*
import java.io.*

/**
 * A local database prototype for working with SQLite databases.
 */
open class SQLite(file: File) : AbstractLocalStorage(DatabaseType.SQLITE, file) {
	override val config: HikariConfig = configurationOf(DatabaseType.SQLITE) {
		jdbcUrl = "jdbc:sqlite:${file.path}"
		isAutoCommit = false
		connectionTestQuery = "SELECT 1"
		transactionIsolation = "TRANSACTION_SERIALIZABLE"
	}
}
