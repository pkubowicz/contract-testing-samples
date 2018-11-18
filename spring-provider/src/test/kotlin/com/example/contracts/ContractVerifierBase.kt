package com.example.contracts

import com.example.Runner
import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before


abstract class ContractVerifierBase {

    private val runner = Runner()

    @Before
    fun startServer() {
        val port = 9053

        RestAssured.port = port
        runner.start(port)
        configure(runner)
    }

    @After
    fun stopServer() {
        runner.stop()
    }

    protected open fun configure(runner: Runner) {}

    protected fun checkSizeIs2(list: List<Any>) {
        checkSizeIs(list, 2)
    }

    protected fun checkSizeIs1(list: List<Any>) {
        checkSizeIs(list, 1)
    }

    private fun checkSizeIs(list: List<Any>, expectedSize: Int) {
        assertThat(list).hasSize(expectedSize)
    }
}
