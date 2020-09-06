plugins {
    kotlin("jvm") version "1.3.72"
    id("org.jmailen.kotlinter") version "1.26.0"
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    api(platform("org.jetbrains.kotlin:kotlin-bom"))
    api(kotlin("stdlib-jdk8"))

    implementation("com.sparkjava:spark-core:2.9.2")

    implementation("com.google.code.gson:gson:2.8.6")

    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.3")

    testImplementation("au.com.dius.pact.provider:junit:4.1.7")
    testRuntimeOnly("com.example:planner-pact:1.0.0-SNAPSHOT")

    testImplementation("junit:junit:4.12")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
    kotlinOptions { jvmTarget = "1.8" }
}
