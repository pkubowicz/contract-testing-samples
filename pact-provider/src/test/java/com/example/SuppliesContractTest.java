package com.example;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactUrl;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("Supplies")
@PactUrl(urls = {"classpath:pact/Planner-Supplies.json"})
public class SuppliesContractTest {

    private final Runner runner = new Runner();

    @BeforeEach
    void setUp(PactVerificationContext context) throws Exception {
        runner.start(9052);
        context.setTarget(new HttpTestTarget("localhost", 9052));
    }

    @AfterEach
    void tearDown() throws Exception {
        runner.stop();
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) throws Exception {
        context.verifyInteraction();
    }
}
