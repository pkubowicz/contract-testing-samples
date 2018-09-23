plugins {
    id("java-library")
}

repositories {
    mavenCentral()
}

dependencies {
	implementation("org.apache.httpcomponents:fluent-hc:4.5.6")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.9.6")

	testImplementation("au.com.dius:pact-jvm-consumer-java8_2.12:3.5.22")
	testImplementation("au.com.dius:pact-jvm-consumer-junit5_2.12:3.5.22")

	testImplementation("org.assertj:assertj-core:3.11.1")
}
