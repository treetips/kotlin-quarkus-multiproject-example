#!/bin/sh

GRAAL_VM_HOME="${HOME}/.sdkman/candidates/java/21.0.2-graalce"
export PATH=${PATH}:${GRAAL_VM_HOME}/bin

./gradlew clean :admin:build \
  -x test \
  -Dquarkus.package.type=native
