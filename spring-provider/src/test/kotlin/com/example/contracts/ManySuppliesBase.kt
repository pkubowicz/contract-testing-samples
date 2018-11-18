package com.example.contracts

import com.example.Server
import com.example.Supply
import com.example.SupplyStatus.ACTIVE
import com.example.SupplyStatus.CANCELED

abstract class ManySuppliesBase : ContractVerifierBase() {

    override fun configure(server: Server) {
        server.supplies = listOf(
                Supply(4, 980, ACTIVE),
                Supply(151, 20, CANCELED),
                Supply(-1, -1, ACTIVE)
        )
    }
}
