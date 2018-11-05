package com.example

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt
import au.com.dius.pact.consumer.junit5.PactTestFor
import au.com.dius.pact.model.RequestResponsePact
import com.example.pact.newObject
import io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(PactConsumerTestExt::class)
@PactTestFor(providerName = "Supplies", port = "9051")
@Disabled
class ContractPerformanceTest {
    private val baseUrl = "http://localhost:9051"

    private lateinit var client: SuppliesClient

    @BeforeEach
    fun setUp() {
        client = SuppliesClient(baseUrl)
    }

    @Pact(consumer = "Planner")
    fun oneSupply0(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .uponReceiving("request for a canceled supply 0")
                .path("/supplies")
                .query("day=2018-12-23")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonArray { array ->
                    array.newObject { o ->
                        o.booleanValue("canceled", true)
                        o.numberType("count", 0)
                        o.numberType("totalWeight", 2*0)
                    }
                }.build())
                .toPact()
    }

    @Test
    @PactTestFor(pactMethod = "oneSupply0")
    fun getsSupplies0() {
        client.getFor(LocalDate.of(2018, 12, 23))
    }
}
