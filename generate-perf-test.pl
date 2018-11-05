#!/usr/bin/perl

for (my $i=0; $i<200; ++$i) {
  print <<"END";

    \@Pact(consumer = "Planner")
    fun oneSupply$i(builder: PactDslWithProvider): RequestResponsePact {
        return builder
                .uponReceiving("request for a canceled supply $i")
                .path("/supplies")
                .query("day=2018-12-23")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonArray { array ->
                    array.newObject { o ->
                        o.booleanValue("canceled", true)
                        o.numberType("count", $i)
                        o.numberType("totalWeight", 2*$i)
                    }
                }.build())
                .toPact()
    }

    \@Test
    \@PactTestFor(pactMethod = "oneSupply$i")
    fun getsSupplies$i() {
        client.getFor(LocalDate.of(2018, 12, 23))
    }
END
}
