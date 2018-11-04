package com.example

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.model.RequestResponsePact
import com.example.pact.newObject
import io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "Supplies", port = "9051")
@Disabled
class StatesTest {
    private lateinit var client: SuppliesClient

    @BeforeEach
    fun setUp() {
        client = SuppliesClient("http://localhost:9051")
    }

    @Pact(consumer = "Planner")
    fun oneSupply(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .given("one canceled and one active supply")
                .uponReceiving("request for a canceled supply")
                .path("/supplies")
                .query("day=2018-12-23")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonArray { array ->
                    array.newObject { o -> o.booleanValue("canceled", true) }
                    array.newObject { o ->
                        o.booleanValue("canceled", false)
                        o.numberValue("count", 4)
                        o.numberValue("totalWeight", 20)
                    }
                }.build())
                .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "oneSupply")
    fun testClient() {
        val received = client.getFor(LocalDate.of(2018, 12, 23))
        assertThat(received).hasSize(2)
        assertThat(received[0])
                .hasFieldOrPropertyWithValue("canceled", true)
        assertThat(received[1])
                .hasFieldOrPropertyWithValue("canceled", false)
                .hasFieldOrPropertyWithValue("count", 4)
                .hasFieldOrPropertyWithValue("totalWeight", 20)
    }

    @Test
    @PactTestFor(pactMethod = "oneSupply")
    fun testLogicAndClient() {
        val averageWeight = SuppliesAnalyser().countAverageItemWeight(
                client.getFor(LocalDate.of(2018, 12, 23)))
        assertThat(averageWeight).isEqualTo(5.0)
    }
}
