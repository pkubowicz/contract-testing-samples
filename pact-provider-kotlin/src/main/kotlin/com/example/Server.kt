package com.example

import com.example.SupplyStatus.CANCELED
import com.google.gson.Gson
import spark.ResponseTransformer
import spark.Route
import spark.Spark

class Server {
    var supplies = listOf(Supply(151, 10, CANCELED))

    fun start(port: Int) {
        val gson = Gson()
        val toJson = ResponseTransformer(gson::toJson)

        Spark.port(port)
        Spark.get("supplies", Route { request, response ->
            response.type("application/json;charset=utf-8")
            supplies
        }, toJson)

        Spark.awaitInitialization()
    }

    fun stop() {
        Spark.stop()
        Spark.awaitStop()
    }
}

fun main() {
    Server().start(8080)
}
