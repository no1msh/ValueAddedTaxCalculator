import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.25"
    `maven-publish`
}

group = "com.no1msh"
version = "1.0.2"

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"]) // Java 컴포넌트 사용
            groupId = "com.github.no1msh"
            artifactId = "vat-calculator"
            version = "1.0.2"
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.6.2")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
    testImplementation("io.kotest:kotest-property:5.6.2")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
