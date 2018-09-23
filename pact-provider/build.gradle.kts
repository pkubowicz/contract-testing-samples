plugins {
    id("java-library")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("io.ratpack:ratpack-core:1.5.4")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("au.com.dius:pact-jvm-provider-junit5_2.12:3.5.22")
    testRuntimeOnly("com.example:planner-pact:1.0.0-SNAPSHOT")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.1.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
