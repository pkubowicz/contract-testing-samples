package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class SuppliesClient {
    private final String baseUrl;
    private final ObjectMapper mapper;

    public SuppliesClient(String baseUrl) {
        this.baseUrl = baseUrl;

        mapper = createMapper();
    }

    public List<Supply> getFor(LocalDate day) {
        try {
            HttpResponse response = Request.Get(baseUrl + "/supplies?day=" + day).execute().returnResponse();
            if (response.getStatusLine().getStatusCode() >= 300) {
                throw errorFor(response);
            }
            return mapper.readValue(
                    EntityUtils.toString(response.getEntity()),
                    new TypeReference<List<Supply>>() { }
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RuntimeException errorFor(HttpResponse response) throws IOException {
        return new RuntimeException("request failed with HTTP " + response.getStatusLine().getStatusCode() + ":\n"
                + EntityUtils.toString(response.getEntity()));
    }

    private static ObjectMapper createMapper() {
        ObjectMapper result = new ObjectMapper();
        result.registerModule(new ParameterNamesModule());
        return result;
    }
}
