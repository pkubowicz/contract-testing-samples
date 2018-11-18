package com.example

import com.example.SupplyStatus.ACTIVE
import com.example.SupplyStatus.CANCELED
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule
import java.time.LocalDate

class SuppliesClientTest {

    companion object {
        @ClassRule
        @JvmField
        var rule: StubRunnerRule = StubRunnerRule()
                .downloadStub("com.example", "spring-provider")
                .withPort(9052)
//                .withStubPerConsumer(true)
//                .withConsumerName("manySupplies")
    }

    private lateinit var client: SuppliesClient

    @Before
    fun setUp() {
        client = SuppliesClient("http://localhost:9052")
    }

    @Test
    fun canReadBothCanceledAndActiveSupplies() {
        val received = client.getFor(LocalDate.of(2018, 12, 23))

        assertThat(received)
                .hasSize(2)
        assertThat(received[0])
                .hasFieldOrPropertyWithValue("status", CANCELED)
        assertThat(received[1])
                .hasFieldOrPropertyWithValue("count", 4)
                .hasFieldOrPropertyWithValue("status", ACTIVE)
                .hasFieldOrPropertyWithValue("totalWeight", 20)
    }
}
