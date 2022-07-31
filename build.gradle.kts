plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "xyz.luccboy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Minestom:Minestom:89a09f326e") {
        exclude("org.jboss.shrinkwrap.resolver", "shrinkwrap-resolver-depchain")
    }
    implementation("org.jboss.shrinkwrap.resolver:shrinkwrap-resolver-impl-maven:3.1.4")
    implementation("com.google.code.gson:gson:2.9.0")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.shadowJar {
    archiveFileName.set("minestom.jar")
    manifest.attributes(Pair("Main-Class", "xyz.luccboy.noobstom.NoobStomLauncher"))
}