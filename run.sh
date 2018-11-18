#!/bin/sh

exec docker run --rm --name=broker -e PACT_BROKER_DATABASE_ADAPTER=sqlite -e PACT_BROKER_DATABASE_NAME=pact_broker.sqlite -p 80:80 pact-broker
