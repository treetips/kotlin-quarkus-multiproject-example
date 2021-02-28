#!/bin/sh

command_name="hello"

./gradlew clean :batch:quarkusDev \
  --quarkus-args="${command_name} --first-name='Quarkus'"
