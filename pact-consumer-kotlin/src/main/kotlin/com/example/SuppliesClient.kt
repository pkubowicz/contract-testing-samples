package com.example

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.http.HttpResponse
import org.apache.http.client.fluent.Request
import org.apache.http.util.EntityUtils
import java.time.LocalDate

class SuppliesClient(private val baseUrl: String) {
    private val mapper = createMapper()

    fun getFor(day: LocalDate): List<Supply> {
        val response = Request.Get("$baseUrl/supplies?day=$day").execute().returnResponse()
        if (response.statusLine.statusCode >= 300) {
            throw errorFor(response)
        }
        return mapper.readValue(
                EntityUtils.toString(response.entity),
                object : TypeReference<List<Supply>>() {}
        )
    }

    private fun errorFor(response: HttpResponse): RuntimeException {
        return RuntimeException("request failed with HTTP " + response.statusLine.statusCode + ":\n" +
                EntityUtils.toString(response.entity))
    }

    private fun createMapper(): ObjectMapper {
        val result = ObjectMapper()
        result.registerModule(KotlinModule())
        return result
    }
}
