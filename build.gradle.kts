plugins {
   kotlin("jvm") version "1.6.10"
   kotlin("plugin.serialization") version "1.6.10"
   id("com.google.devtools.ksp") version "1.6.10-1.0.2"
   id("java")
   id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "net.arfay"
version = "1.0-SNAPSHOT"

allprojects {

   apply(plugin = "org.jetbrains.kotlin.jvm")
   apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
   apply(plugin = "com.google.devtools.ksp")
   apply(plugin = "java")
   apply(plugin = "com.github.johnrengelman.shadow")

   repositories {
      mavenCentral()

      maven(url = "https://repo.aikar.co/content/groups/aikar/")
      maven(url = "https://jitpack.io")
   }

   dependencies {

      // spigot
      compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")

      // kotlin
      api(kotlin("stdlib-jdk8"))
      api(kotlin("kotlin-reflect"))
      api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")

      // database
      api("org.jetbrains.exposed:exposed-core:0.37.3")
      api("org.jetbrains.exposed:exposed-dao:0.37.3")
      api("org.jetbrains.exposed:exposed-jdbc:0.37.3")
      api("com.zaxxer:HikariCP:5.0.1")
      api("org.xerial:sqlite-jdbc:3.36.0.1")

      // serialization
      api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.3")
      api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
      api("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3")
      api("com.charleskorn.kaml:kaml:0.46.0")
      api("net.benwoodworth.knbt:knbt:0.11.1")

      // korlibs
      api("com.soywiz.korlibs.korma:korma-jvm:3.0.0-Beta6")
      api("com.soywiz.korlibs.korim:korim-jvm:3.0.0-Beta6")
      api("com.soywiz.korlibs.klock:klock-jvm:3.0.0-Beta6")
      api("com.soywiz.korlibs.krypto:krypto-jvm:3.0.0-Beta6")
      api("com.soywiz.korlibs.kmem:kmem-jvm:3.0.0-Beta6")
      api("com.soywiz.korlibs.kds:kds-jvm:3.0.0-Beta6")
      api("com.soywiz.korlibs.korio:korio-jvm:3.0.0-Beta6")

      // others
      api("com.github.ben-manes.caffeine:caffeine:3.0.6")
      api("it.unimi.dsi:fastutil:8.5.8")
      api("net.jafama:jafama:2.3.2")

      compileOnly("io.github.uinnn:serializer-framework:2.4.0")

      compileOnly("org.projectlombok:lombok:1.18.22")
      annotationProcessor("org.projectlombok:lombok:1.18.22")
   }


   tasks {
      withType<GenerateModuleMetadata> {
         isEnabled = false
      }

      shadowJar {
         // applies filters to not include some dependencies on the shadow jar
         // since we'll use repository-lib to provide all dependency to make
         // the final shadow jar smaller.
         dependencyFilter = dependencyFilter.apply {

            // kotlin
            exclude(dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2"))

            // database
            exclude(dependency("org.jetbrains.exposed:exposed-core:0.37.3"))
            exclude(dependency("org.jetbrains.exposed:exposed-dao:0.37.3"))
            exclude(dependency("org.jetbrains.exposed:exposed-jdbc:0.37.3"))
            exclude(dependency("com.zaxxer:HikariCP:5.0.1"))
            exclude(dependency("org.xerial:sqlite-jdbc:3.36.0.1"))

            // serialization
            exclude(dependency("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.3"))
            exclude(dependency("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"))
            exclude(dependency("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3"))
            exclude(dependency("com.charleskorn.kaml:kaml:0.46.0"))
            exclude(dependency("net.benwoodworth.knbt:knbt:0.11.1"))

            // korlibs
            exclude(dependency("com.soywiz.korlibs.korma:korma-jvm:3.0.0-Beta6"))
            exclude(dependency("com.soywiz.korlibs.korim:korim-jvm:3.0.0-Beta6"))
            exclude(dependency("com.soywiz.korlibs.klock:klock-jvm:3.0.0-Beta6"))
            exclude(dependency("com.soywiz.korlibs.krypto:krypto-jvm:3.0.0-Beta6"))
            exclude(dependency("com.soywiz.korlibs.kmem:kmem-jvm:3.0.0-Beta6"))
            exclude(dependency("com.soywiz.korlibs.kds:kds-jvm:3.0.0-Beta6"))
            exclude(dependency("com.soywiz.korlibs.korio:korio-jvm:3.0.0-Beta6"))

            // others
            exclude(dependency("com.github.ben-manes.caffeine:caffeine:3.0.6"))
            exclude(dependency("it.unimi.dsi:fastutil:8.5.8"))
            exclude(dependency("net.jafama:jafama:2.3.2"))
         }
      }
   }
}
