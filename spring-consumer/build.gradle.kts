plugins {
    kotlin("jvm") version "1.3.21"
}

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib-jdk8", "1.3.21"))
    constraints {
        implementation(kotlin("reflect", "1.3.21"))
    }

    implementation("org.apache.httpcomponents:fluent-hc:4.5.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.8")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

    testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.11.2")

    testImplementation(platform("org.springframework.cloud:spring-cloud-contract-dependencies:2.1.0.RELEASE"))
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner") {
        exclude(group = "ch.qos.logback")
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
}
