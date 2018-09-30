package com.example;

import java.util.List;

public class SuppliesAnalyser {

    public double countAverageItemWeight(List<Supply> supplies) {
        int sumWeights = supplies.stream()
                .mapToInt(Supply::getTotalWeight)
                .sum();
        int totalItems = supplies.stream()
                .mapToInt(Supply::getCount)
                .sum();
        return (double) sumWeights / totalItems;
    }
}
