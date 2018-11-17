package com.example

import io.restassured.RestAssured
import org.junit.After
import org.junit.Before


open class ContractTestBase {

    val port: Int = 9053

    val runner = Runner()

    @Before
    fun startServer() {
        runner.start(port)
        RestAssured.port = this.port
    }

    @After
    fun stopServer() {
        runner.stop()
    }
}
