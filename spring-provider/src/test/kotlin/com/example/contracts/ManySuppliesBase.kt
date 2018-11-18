package com.example.contracts

import com.example.Runner
import com.example.Supply
import com.example.SupplyStatus

abstract class ManySuppliesBase : ContractVerifierBase() {

    override fun configure(runner: Runner) {
        runner.supplies = listOf(
                Supply(4, 10, SupplyStatus.ACTIVE),
                Supply(151, 20, SupplyStatus.CANCELED)
//                Supply(-1, -1, ACTIVE)
        )
    }
}
