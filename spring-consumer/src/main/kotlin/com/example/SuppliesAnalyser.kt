package com.example

class SuppliesAnalyser {

    fun countAverageItemWeight(supplies: List<Supply>): Double {
        val totalItems = supplies.filter(this::notCanceled).map { it.count }.sum()
        if (totalItems == 0) {
            return 0.0
        }

        val sumWeights = supplies.filter(this::notCanceled).map { it.totalWeight }.sum()
        return sumWeights.toDouble() / totalItems
    }

    private fun notCanceled(supply: Supply) = supply.status != SupplyStatus.CANCELED
}
