#!/bin/bash

# Script to run the Polymarket API demo
# This will start the application with the 'demo' profile

echo "Starting Polymarket API Demo..."
./gradlew bootRun --args='--spring.profiles.active=demo'