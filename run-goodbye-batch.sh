#!/bin/sh

command_name="goodbye"

./gradlew clean :batch:quarkusDev \
  --quarkus-args="${command_name} --name='Quarkus' --times=3"
