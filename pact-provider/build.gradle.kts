plugins {
    id("java-library")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("io.ratpack:ratpack-core:1.5.4")

    compileOnly("org.projectlombok:lombok:1.18.2")
    annotationProcessor("org.projectlombok:lombok:1.18.2")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("au.com.dius:pact-jvm-provider-junit_2.12:3.5.23")
    testRuntimeOnly("com.example:planner-pact:1.0.0-SNAPSHOT")

    testImplementation("junit:junit:4.12")
}
