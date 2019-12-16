package com.example

import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.core.model.RequestResponsePact
import au.com.dius.pact.core.model.annotations.Pact
import com.example.SupplyStatus.ACTIVE
import com.example.SupplyStatus.CANCELED
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
    fun twoSupplies(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .given("one canceled and one active supply")
                .uponReceiving("request for two supplies")
                .path("/supplies")
                .query("day=2018-12-23")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(
                        newJsonArray { array ->
                            array.newObject { o -> o.stringValue("status", "CANCELED") }
                            array.newObject { o ->
                                o.numberValue("count", 4)
                                o.numberValue("totalWeight", 20)
                                o.stringValue("status", "ACTIVE")
                            }
                        }.build()
                )
                .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "twoSupplies")
    fun testClient() {
        val received = client.getFor(LocalDate.of(2018, 12, 23))

        assertThat(received).hasSize(2)
        assertThat(received[0])
                .hasFieldOrPropertyWithValue("status", CANCELED)
        assertThat(received[1])
                .hasFieldOrPropertyWithValue("count", 4)
                .hasFieldOrPropertyWithValue("status", ACTIVE)
                .hasFieldOrPropertyWithValue("totalWeight", 20)
    }

    @Test
    @PactTestFor(pactMethod = "twoSupplies")
    fun testLogicAndClient() {
        val averageWeight = SuppliesAnalyser().countAverageItemWeight(
                client.getFor(LocalDate.of(2018, 12, 23))
        )
        assertThat(averageWeight).isEqualTo(5.0)
    }
}
