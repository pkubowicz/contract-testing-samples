package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(PER_CLASS)
class SuppliesAnalyserTest {
    private val analyser = SuppliesAnalyser()

    private fun differentSupplies() : Stream<Arguments> {
        return Stream.of(
                Arguments.of(
                        listOf(Supply(1, 13, false)),
                        13.0
                ),
                Arguments.of(
                        listOf(Supply(2, 12, false), Supply(3, 13, false)),
                        5.0
                )
        )
    }

    @ParameterizedTest
    @MethodSource("differentSupplies")
    fun countsAverage(supplies: List<Supply>, expectedAverage: Double) {
        assertThat(analyser.countAverageItemWeight(supplies))
                .isEqualTo(expectedAverage)
    }
}
