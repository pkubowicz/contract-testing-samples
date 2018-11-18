package com.example.contracts

import com.example.Server
import com.example.Supply
import com.example.SupplyStatus

abstract class OneSupplyBase : ContractVerifierBase() {

    override fun configure(server: Server) {
        server.supplies = listOf(
                Supply(151, 20, SupplyStatus.CANCELED)
        )
    }
}
