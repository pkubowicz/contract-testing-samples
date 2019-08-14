package com.example.contracts

import com.example.Server
import io.restassured.RestAssured
import org.junit.After
import org.junit.Before

abstract class ContractVerifierBase {

    private val server = Server()

    @Before
    fun startServer() {
        val port = 9053

        RestAssured.port = port
        server.start(port)
        configure(server)
    }

    @After
    fun stopServer() {
        server.stop()
    }

    protected open fun configure(server: Server) {}
}
