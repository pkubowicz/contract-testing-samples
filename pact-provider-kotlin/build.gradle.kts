plugins {
    kotlin("jvm") version "1.3.41"
    id("org.jmailen.kotlinter") version "1.26.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api(kotlin("stdlib-jdk8", "1.3.41"))
    constraints {
        implementation(kotlin("reflect", "1.3.41"))
    }

    implementation("com.sparkjava:spark-core:2.9.1")

    implementation("com.google.code.gson:gson:2.8.5")

    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.12.1")

    testImplementation("au.com.dius:pact-jvm-provider-junit_2.12:3.6.12")
    testRuntimeOnly("com.example:planner-pact:1.0.0-SNAPSHOT")

    testImplementation("junit:junit:4.12")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
    kotlinOptions { jvmTarget = "1.8" }
}
