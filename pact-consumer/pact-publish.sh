#!/bin/sh

docker run --rm --name=broker -e PACT_BROKER_DATABASE_ADAPTER=sqlite -e PACT_BROKER_DATABASE_NAME=pact_broker.sqlite -p 80:80 dius/pact-broker

cat build/pact/Planner-Supplies.json| curl -XPUT -H "Content-Type: application/json" --data @- http://localhost:80/pacts/provider/Supplies/consumer/Planner/version/1.0.0
