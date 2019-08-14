group = "com.example"
version = "1.0.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.41"
    id("org.jmailen.kotlinter") version "1.26.0"
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib-jdk8", "1.3.41"))
    constraints {
        implementation(kotlin("reflect", "1.3.41"))
    }

    implementation("org.apache.httpcomponents:fluent-hc:4.5.9")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.9.3")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.9")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.9")

    testImplementation("au.com.dius:pact-jvm-consumer-java8_2.12:3.6.12")
    testImplementation("au.com.dius:pact-jvm-consumer-junit5_2.12:3.6.12")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")

    testImplementation("org.assertj:assertj-core:3.13.2")

    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.12.1")
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
