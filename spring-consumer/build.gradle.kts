plugins {
    kotlin("jvm") version "1.3.61"
    id("org.jmailen.kotlinter") version "1.26.0"
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

    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.0")

    testImplementation(platform("org.springframework.cloud:spring-cloud-contract-dependencies:2.2.1.RELEASE"))
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner") {
        exclude(group = "ch.qos.logback")
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
}
