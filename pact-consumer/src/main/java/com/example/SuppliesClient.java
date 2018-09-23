package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SuppliesClient {
    private final String baseUrl;
    private final ObjectMapper mapper = new ObjectMapper();

    public SuppliesClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Supply> getFor(LocalDate day) {
        try {
            return mapper.readValue(
                    Request.Get(baseUrl + "/supplies").execute().returnContent().asString(),
                    new TypeReference<List<Supply>>() { }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
