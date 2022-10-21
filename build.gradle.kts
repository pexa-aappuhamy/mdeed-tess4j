import org.jetbrains.kotlin.gradle.tasks.KotlinCompile






plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "me.aappuhamy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

dependencies {
    implementation(files("libs/tess4j-5.4.0.jar"))
    implementation("net.java.dev.jna:jna-platform-jpms:5.12.1")
    implementation("net.sourceforge.lept4j:lept4j:1.16.2")
    implementation("com.guicedee.services:sl4j:1.0.13.5-jre14")
    implementation("org.lucee:pdfbox:3.0.0.RC102")
}