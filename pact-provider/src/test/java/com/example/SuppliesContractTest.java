package com.example;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactUrl;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("Supplies")
@PactUrl(urls = {"classpath:pact/Planner-Supplies.json"})
public class SuppliesContractTest {

    private final Runner runner = new Runner();
    @TestTarget
    public final Target target = new HttpTarget(9052);

    @Before
    public void setUp() throws Exception {
        runner.start(9052);
    }

    @After
    public void tearDown() throws Exception {
        runner.stop();
    }
}
