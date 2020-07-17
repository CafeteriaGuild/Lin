plugins {
    java
    application
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "io.github.cafeteriaguild"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

application.mainClassName = "io.github.cafeteriaguild.lin.LinUtilsKt"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.adriantodt:tartar:1.3")
    //implementation("it.unimi.dsi:fastutil:8.3.1")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}