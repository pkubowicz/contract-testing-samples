package com.example;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Supplies", port = "9051")
public class SuppliesContractTest {
    private static final String BASE_URL = "http://localhost:9051";
    private SuppliesClient client;

    @BeforeEach
    void setUp() {
        client = new SuppliesClient(BASE_URL);
    }

    @Pact(consumer="Planner")
    public RequestResponsePact oneSupply(PactDslWithProvider builder) {
        return builder
                .uponReceiving("request for a canceled supply")
                .path("/supplies")
                .query("day=2018-12-23")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonArray(array -> {
                    array.object(o -> {
                        o.booleanValue("canceled", true);
                    });
                }).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "oneSupply")
    public void getsSupplies() {
        client.getFor(LocalDate.of(2018, 12, 23));
    }

    @Pact(consumer="Planner")
    public RequestResponsePact malformedDate(PactDslWithProvider builder) {
        return builder
                .uponReceiving("request with malformed date")
                .path("/supplies")
                .query("day=abc")
                .method("GET")
                .willRespondWith()
                .status(400)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "malformedDate")
    public void getWithMalformed() throws Exception {
        Request.Get(BASE_URL + "/supplies?day=abc").execute();
    }
}
