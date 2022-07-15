/*
Copyright (C) 2022 Arfay

You may not use this file except in compliance with the Team Agreement.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*/


package net.arfay.sdk.database

/**
 * A type of database, representing all supported databases.
 */
enum class DatabaseType(val isServer: Boolean, val driver: String) {
	POSTGRE(true, "org.postgresql.Driver"),
	MYSQL(true, "com.mysql.jdbc.Driver"),
	H2(false, "org.h2.Driver"),
	SQLITE(false, "org.sqlite.JDBC");
	
	val isLocal: Boolean get() = !isServer
}
