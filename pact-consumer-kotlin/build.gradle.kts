group = "com.example"
version = "1.0.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.61"
    id("org.jmailen.kotlinter") version "1.26.0"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    api(platform("org.jetbrains.kotlin:kotlin-bom"))
    api(kotlin("stdlib-jdk8"))

    implementation("org.apache.httpcomponents:fluent-hc:4.5.10")
    implementation(platform("com.fasterxml.jackson:jackson-bom:2.10.1"))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    testImplementation("au.com.dius:pact-jvm-consumer-java8:4.0.3")
    testImplementation("au.com.dius:pact-jvm-consumer-junit5:4.0.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.2")

    testImplementation("org.assertj:assertj-core:3.14.0")

    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
    kotlinOptions { jvmTarget = "1.8" }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()

    systemProperty("pact.rootDir", "$buildDir/pact")
    outputs.dir("$buildDir/pact")

    doFirst {
        delete("$buildDir/pact") // prevent strange 'merging' done by Pact
    }
}

task<Jar>("pactJar") {
    dependsOn("test")

    from(buildDir)
    include("pact/**")
}

publishing {
    publications {
        create<MavenPublication>("pact") {
            artifactId = "planner-pact"
            artifact(tasks.getByName("pactJar"))
        }
    }
}
