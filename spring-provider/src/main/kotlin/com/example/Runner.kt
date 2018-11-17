package com.example

import com.example.SupplyStatus.CANCELED
import com.google.gson.Gson
import spark.ResponseTransformer
import spark.Route
import spark.Spark
import java.time.LocalDate

class Runner {
    fun start(port: Int) {
        val gson = Gson()
        val toJson = ResponseTransformer(gson::toJson)

        val supplies = listOf(Supply(151, 10, CANCELED))

        Spark.port(port)
        Spark.get("supplies", Route { request, response ->
            try {
                LocalDate.parse(request.queryParams("day"))
            } catch (e: Exception) {
                response.status(400)
                return@Route null
            }
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
    Runner().start(8080)
}
