Sample projects using Contract Testing. There are two pairs: `pact-consumer-kotlin` + `pact-provider-kotlin` and `spring-consumer` + `spring-provider`. Business logic and networking stack is the same for both pairs, also the logic (but not the format!) of the contract is identical, the only difference is the framework used to write and execute Contract Tests, so that you can easily observe similarities and differences between [Pact](https://pact.io) and [Spring Cloud Contract](https://cloud.spring.io/spring-cloud-contract/).

The code is written in Kotlin and built using Gradle.

The workflow is:

```
cd pact-consumer-kotlin
./gradlew publishToMavenLocal
cd ../pact-provider-kotlin
./gradlew build
```
(`Planner - request with malformed date` is deliberately left as failing to demonstrate an unimplement feature failing tests)

```
cd spring-provider
./gradlew publishToMavenLocal
cd ../spring-consumer
./gradlew build
```
