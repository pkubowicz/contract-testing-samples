group = "com.example"
version = "1.0.0-SNAPSHOT"

plugins {
    id("java-library")
    id("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.httpcomponents:fluent-hc:4.5.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.6")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.6")

    testImplementation("au.com.dius:pact-jvm-consumer-java8_2.12:3.5.22")
    testImplementation("au.com.dius:pact-jvm-consumer-junit5_2.12:3.5.22")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.1.0")

    testImplementation("org.assertj:assertj-core:3.11.1")

    testRuntimeOnly("ch.qos.logback:logback-classic:1.2.3")
}

tasks.getByName<JavaCompile>("compileJava") {
    options.compilerArgs.add("-parameters")
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
