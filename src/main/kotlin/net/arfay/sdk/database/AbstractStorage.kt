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
 * A abstract implementation of [Storage].
 * This is a skeletal model for creating new database prototypes.
 */
abstract class AbstractStorage(override val type: DatabaseType) : Storage {
	override val source: DataSource by lazy { HikariDataSource(config) }
	override val database: Database by lazy { Database.connect(source) }
}

/**
 * A abstract implementation of [LocalStorage].
 * This is a skeletal model for creating new local database prototypes.
 */
abstract class AbstractLocalStorage(
    type: DatabaseType,
    override var file: File,
) : AbstractStorage(type), LocalStorage {
	init {
		file.parentFile?.mkdirs()
		if (type == DatabaseType.SQLITE && !file.exists()) {
			file.createNewFile()
		}
	}
}

/**
 * A abstract implementation of [ServerStorage].
 * This is a skeletal model for creating new server database prototypes.
 */
abstract class AbstractServerStorage(
    type: DatabaseType,
    override var username: String,
    override var password: String,
    override var databaseName: String,
    override var host: String,
    override var port: Int = 3306,
    override var useSSl: Boolean = false,
) : AbstractStorage(type), ServerStorage
