plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
}

dependencies {
    implementation(project(":database"))
    implementation(project(":repository"))
}

bukkit {
    main = "net.arfay.sdk.Main"
    version = "${project.version}"
    author = "Gabrideiros"
}