package com.example.contracts

import com.example.Runner
import com.example.Supply
import com.example.SupplyStatus

abstract class OneSupplyBase : ContractVerifierBase() {

    override fun configure(runner: Runner) {
        runner.supplies = listOf(
                Supply(151, 20, SupplyStatus.CANCELED)
        )
    }
}
