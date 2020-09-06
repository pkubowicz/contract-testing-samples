package com.example

import au.com.dius.pact.provider.junit.PactRunner
import au.com.dius.pact.provider.junit.target.HttpTarget
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.loader.PactUrl
import au.com.dius.pact.provider.junitsupport.target.TestTarget
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(PactRunner::class)
@Provider("Supplies")
@PactUrl(urls = ["classpath:pact/Planner-Supplies.json"])
class ServerContractTest {

    private val server = Server()

    @TestTarget
    val target = HttpTarget(port = 9052)

    @Before
    fun setUp() {
        server.start(9052)
    }

    @After
    fun tearDown() {
        server.stop()
    }
}
