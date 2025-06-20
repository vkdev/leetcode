plugins {
    kotlin("jvm") version "2.1.20"
}

group = "com.github.vkdev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}