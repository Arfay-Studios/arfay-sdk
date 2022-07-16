/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package arfay.database.factory

import arfay.database.*
import com.zaxxer.hikari.*

typealias ConfigurationBuilder = HikariConfig.() -> Unit

/**
 * Creates a new hikari config with the specified builder.
 */
fun createConfiguration(builder: ConfigurationBuilder): HikariConfig {
	return HikariConfig().apply(builder)
}

/**
 * Creates a new hikari config with the type of database
 * and the specified builder.
 */
fun configurationOf(type: DatabaseType, builder: ConfigurationBuilder): HikariConfig {
	return HikariConfig().apply {
		driverClassName = type.driver
		builder(this)
	}
}
