package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SuppliesAnalyserTest {
    private SuppliesAnalyser analyser = new SuppliesAnalyser();

    @Test
    void countsAverage() {
        var supplies = List.of(new Supply(2, 10, false),
                new Supply(3, 15, false));
        assertThat(analyser.countAverageItemWeight(supplies))
                .isEqualTo(5.0);
    }
}
