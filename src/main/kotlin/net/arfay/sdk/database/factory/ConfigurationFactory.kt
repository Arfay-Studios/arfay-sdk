/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/

package net.arfay.sdk.database.factory

import com.zaxxer.hikari.*
import net.arfay.sdk.database.DatabaseType

typealias ConfigurationBuilder = HikariConfig.() -> Unit

/**
 * A factory object used for creating new [HikariConfig].
 */
object ConfigurationFactory {
	
	/**
	 * Creates a new hikari config with the specified builder.
	 */
	fun create(builder: ConfigurationBuilder): HikariConfig {
		return HikariConfig().apply(builder)
	}
	
	/**
	 * Creates a new hikari config with the type of database
	 * and the specified builder.
	 */
	fun of(type: DatabaseType, builder: ConfigurationBuilder): HikariConfig {
		return HikariConfig().apply {
			driverClassName = type.driver
			builder(this)
		}
	}
}

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
