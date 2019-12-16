#!/bin/sh

exec docker run --rm --name=broker -e PACT_BROKER_DATABASE_ADAPTER=sqlite -e PACT_BROKER_DATABASE_NAME=pact_broker.sqlite -p 9292:9292 pactfoundation/pact-broker
