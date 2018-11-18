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
        private const val port = 9052

        @ClassRule
        @JvmField
        var rule: StubRunnerRule = StubRunnerRule()
                .downloadStub("com.example", "spring-provider")
                .withPort(port)
    }

    private lateinit var client: SuppliesClient

    @Before
    fun setUp() {
        client = SuppliesClient("http://localhost:$port")
    }

    @Test
    fun canReadBothCanceledAndActiveSupplies() {
        val supplies = client.getFor(LocalDate.of(2018, 12, 23))
        assertThat(supplies)
                .hasSize(2)
        assertThat(supplies[0])
                .hasFieldOrPropertyWithValue("status", CANCELED)
        assertThat(supplies[1])
                .hasFieldOrPropertyWithValue("count", 4)
                .hasFieldOrPropertyWithValue("status", ACTIVE)
                .hasFieldOrPropertyWithValue("totalWeight", 20)
    }
}
