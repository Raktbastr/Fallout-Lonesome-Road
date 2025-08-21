plugins {
    id("java")
    application
}

group = "net.halfheart"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("net.halfheart.Main")
}

tasks.test {
    useJUnitPlatform()
}

// Optional: Fat jar configuration (remove if not needed)
tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
    // Unpack runtime dependencies into the jar
    from(
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith(".jar") }
            .map { zipTree(it) }
    )
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    isZip64 = true
}