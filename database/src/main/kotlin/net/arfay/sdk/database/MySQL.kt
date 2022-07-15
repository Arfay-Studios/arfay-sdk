/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.database

import com.zaxxer.hikari.*
import net.arfay.sdk.database.factory.configurationOf

/**
 * A server database prototype for working with MySQL databases.
 */
open class MySQL(
	username: String,
	password: String,
	databaseName: String,
	host: String,
	port: Int = 3306,
	useSSl: Boolean = false,
) : AbstractServerStorage(DatabaseType.MYSQL, username, password, databaseName, host, port, useSSl) {
	override val config: HikariConfig = configurationOf(DatabaseType.MYSQL) {
		jdbcUrl = "jdbc:mysql://$host:$port/$databaseName?useSSL=$useSSl"
		this.username = username
		this.password = password
		isAutoCommit = false
		addDataSourceProperty("cachePrepStmts", "true")
		addDataSourceProperty("prepStmtCacheSize", "350")
		addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
		addDataSourceProperty("useServerPrepStmts", "true")
		addDataSourceProperty("createDatabaseIfNotExist", "true")
	}
}
