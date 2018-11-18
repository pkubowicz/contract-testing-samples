package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule
import java.time.LocalDate

class SuppliesAnalyserTest {

    companion object {
        @ClassRule
        @JvmField
        var rule: StubRunnerRule = StubRunnerRule()
                .downloadStub("com.example", "spring-provider")
                .withStubPerConsumer(true)
                .withConsumerName("oneSupply")
    }

    private lateinit var client: SuppliesClient
    private lateinit var analyser: SuppliesAnalyser

    @Before
    fun setUp() {
        val port = rule.findStubUrl("com.example", "spring-provider").port
        client = SuppliesClient("http://localhost:$port")
        analyser = SuppliesAnalyser()
    }

    @Test
    fun countsZeroIfAllSuppliesAreCanceled() {
        val averageWeight = analyser.countAverageItemWeight(
                client.getFor(LocalDate.of(2018, 12, 23))
        )
        assertThat(averageWeight).isEqualTo(0.0)
    }
}
