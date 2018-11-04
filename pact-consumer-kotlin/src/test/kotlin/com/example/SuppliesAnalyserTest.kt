package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SuppliesAnalyserTest {
    private val analyser = SuppliesAnalyser()

    @Test
    fun countsAverage() {
        val supplies = listOf(Supply(2, 10, false),
                Supply(3, 15, false))
        assertThat(analyser.countAverageItemWeight(supplies))
                .isEqualTo(5.0)
    }
}
