package com.example

import org.junit.Before
import org.springframework.boot.web.server.LocalServerPort



open class ContractTestBase {

    @LocalServerPort
    var port: Int = 0

    @Before
    fun startServer() {
        // will start
    }
}
