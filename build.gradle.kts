plugins {
   kotlin("jvm") version "1.7.10"
   kotlin("plugin.serialization") version "1.7.10"
   id("java")
   id("com.github.johnrengelman.shadow") version "7.0.0"
}

val APIDependencies = listOf(
   
   // kotlin
   "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10",
   "org.jetbrains.kotlin:kotlin-reflect:1.7.10",
   "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2",
   
   // database
   "org.jetbrains.exposed:exposed-core:0.37.3",
   "org.jetbrains.exposed:exposed-dao:0.37.3",
   "org.jetbrains.exposed:exposed-jdbc:0.37.3",
   "com.zaxxer:HikariCP:5.0.1",
   "org.xerial:sqlite-jdbc:3.36.0.1",
   
   // serialization
   "org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.3",
   "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3",
   "org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.3",
   "com.charleskorn.kaml:kaml:0.46.0",
   "net.benwoodworth.knbt:knbt:0.11.1",
   
   // korlibs
   "com.soywiz.korlibs.korma:korma-jvm:3.0.0-Beta6",
   "com.soywiz.korlibs.korim:korim-jvm:3.0.0-Beta6",
   "com.soywiz.korlibs.klock:klock-jvm:3.0.0-Beta6",
   "com.soywiz.korlibs.krypto:krypto-jvm:3.0.0-Beta6",
   "com.soywiz.korlibs.kmem:kmem-jvm:3.0.0-Beta6",
   "com.soywiz.korlibs.kds:kds-jvm:3.0.0-Beta6",
   "com.soywiz.korlibs.korio:korio-jvm:3.0.0-Beta6",
   
   // apache
   "org.apache.commons:commons-lang3:3.12.0",
   "org.apache.commons:commons-collections4:4.4",
   "org.apache.commons:commons-math3:3.6.1",
   "org.apache.commons:commons-compress:1.21",
   "org.apache.commons:commons-text:1.9",
   "commons-io:commons-io:2.11.0",
   
   // others
   "com.github.ben-manes.caffeine:caffeine:3.0.6",
   "it.unimi.dsi:fastutil:8.5.8",
   "net.jafama:jafama:2.3.2"
)

allprojects {
   
   apply(plugin = "org.jetbrains.kotlin.jvm")
   apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
   apply(plugin = "java")
   apply(plugin = "com.github.johnrengelman.shadow")
   
   group = "net.arfay"
   version = "1.0-SNAPSHOT"
   
   repositories {
      mavenCentral()
      maven(url = "https://repo.aikar.co/content/groups/aikar/")
      maven(url = "https://jitpack.io")
   }
   
   dependencies {
      APIDependencies.forEach(::api)
      
      // spigot
      compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
      
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
            APIDependencies.forEach {
               exclude(dependency(it))
            }
         }
      }
   }
}
