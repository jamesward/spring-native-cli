plugins {
    kotlin("jvm")                              version "1.6.10"
    kotlin("plugin.spring")                    version "1.6.10"
    id("org.springframework.boot")             version "2.6.2"
    id("io.spring.dependency-management")      version "1.0.11.RELEASE"
    id("org.springframework.experimental.aot") version "0.11.1"
}

repositories {
    mavenCentral()
    maven("https://repo.spring.io/release")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.springframework.boot:spring-boot-starter")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootBuildImage> {
    val args = setOf(
        "-Dspring.spel.ignore=true",
        "-Dspring.native.remove-yaml-support=true"
    )
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf(
        "BP_NATIVE_IMAGE" to "1",
        "BP_NATIVE_IMAGE_BUILD_ARGUMENTS" to args.joinToString(" ")
    )
}
