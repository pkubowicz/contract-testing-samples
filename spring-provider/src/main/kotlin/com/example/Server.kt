package com.example

import com.google.gson.Gson
import spark.ResponseTransformer
import spark.Route
import spark.Spark
import java.time.LocalDate

class Server {
    var supplies = emptyList<Supply>()

    fun start(port: Int) {
        val gson = Gson()
        val toJson = ResponseTransformer(gson::toJson)

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
    Server().start(8080)
}
