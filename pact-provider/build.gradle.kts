plugins {
    id("java-library")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("au.com.dius:pact-jvm-provider-junit5_2.12:3.5.22")
}
