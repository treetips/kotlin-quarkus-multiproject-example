#!/bin/sh

GRAAL_VM_HOME="${HOME}/.sdkman/candidates/java/22-graalce"

run() {
  projectName="$1"
  ./gradlew clean :${projectName}:quarkusDev
}

buildNative() {
  projectName="$1"
  export PATH=${PATH}:${GRAAL_VM_HOME}/bin
  ./gradlew clean :${projectName}:build \
    -x test \
    -Dquarkus.package.type=native
}
