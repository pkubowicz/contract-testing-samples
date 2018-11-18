package com.example

class SuppliesAnalyser {

    fun countAverageItemWeight(supplies: List<Supply>): Double {
        val sumWeights = supplies.map { it.totalWeight }.sum()
        val totalItems = supplies.map { it.count }.sum()
        return sumWeights.toDouble() / totalItems
    }
}
