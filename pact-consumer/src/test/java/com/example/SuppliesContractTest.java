package com.example;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "Supplies", port = "9051")
public class SuppliesContractTest {
    private SuppliesClient client;

    @BeforeEach
    void setUp() {
        client = new SuppliesClient("http://localhost:9051");
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

    @Test
    @PactTestFor(pactMethod = "oneSupply")
    @Disabled
    public void getsSupplies2() {
        double counted = new SuppliesAnalyser().countAverageItemWeight(client.getFor(LocalDate.of(2018, 12, 23)));
        assertThat(counted).isEqualTo(0.0d);
    }
}
