/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.database

import com.zaxxer.hikari.*
import org.jetbrains.exposed.sql.*
import java.io.*
import javax.sql.*

/**
 * Represents a database prototype, this is, a model for creating
 * custom databases, with custom data sources, configurations and more.
 */
interface Storage {
	
	/**
	 * The type of this database prototype.
	 */
	val type: DatabaseType
	
	/**
	 * The [HikariConfig] configuration of this database prototype.
	 */
	val config: HikariConfig
	
	/**
	 * The [DataSource] source of this database prototype.
	 */
	val source: DataSource
	
	/**
	 * The final database of this database prototype.
	 */
	val database: Database
}

/**
 * A local database implementation for use with [Storage].
 */
interface LocalStorage : Storage {
	
	/**
	 * The local file used in this local database prototype.
	 */
	var file: File
}

/**
 * A server database implementation for use with [Storage]
 */
interface ServerStorage : Storage {
	
	/**
	 * The username of this server database prototype.
	 */
	var username: String
	
	/**
	 * The password of this server database prototype.
	 */
	var password: String
	
	/**
	 * The host of this server database prototype.
	 */
	var host: String
	
	/**
	 * The port of this server database prototype.
	 */
	var port: Int
	
	/**
	 * The database name of this server database prototype.
	 */
	var databaseName: String
	
	/**
	 * If this server database prototype uses secure socket layer (SSL) in connections.
	 */
	var useSSl: Boolean
}
