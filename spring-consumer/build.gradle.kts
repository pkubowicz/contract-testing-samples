plugins {
    kotlin("jvm") version "1.3.41"
    id("org.jmailen.kotlinter") version "1.26.0"
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

    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.12.1")

    testImplementation(platform("org.springframework.cloud:spring-cloud-contract-dependencies:2.1.2.RELEASE"))
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner") {
        exclude(group = "ch.qos.logback")
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
}
