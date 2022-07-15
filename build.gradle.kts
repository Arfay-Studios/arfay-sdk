plugins {
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.serialization") version "1.6.10"
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("signing")
}

group = "net.arfay"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven(url = "https://repo.aikar.co/content/groups/aikar/")
    maven(url = "https://jitpack.io")
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

    compileOnly("io.github.uinnn:serializer-framework:2.4.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2")

    api(kotlin("stdlib-jdk8"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    api("com.zaxxer:HikariCP:4.0.3")
    api("org.jetbrains.exposed:exposed-core:0.37.3")
    api("org.jetbrains.exposed:exposed-dao:0.37.3")
    api("org.jetbrains.exposed:exposed-jdbc:0.37.3")
    api("org.xerial:sqlite-jdbc:3.36.0.1")
}