# kotlin-quarkus-multi-project-example

## Features

| Name                                                                    | Version | Note                                                                                            |
|-------------------------------------------------------------------------|---------|-------------------------------------------------------------------------------------------------|
| [Intellij IDEA](https://www.jetbrains.com/idea/)                        | latest  | Required Plugins: [ktfmt](https://plugins.jetbrains.com/plugin/14912-ktfmt)                     |
| [Kotlin](https://kotlinlang.org/)                                       | 1.9     | With Java v21                                                                                   |
| [Gradle](https://gradle.org/)                                           | 8.6     |                                                                                                 |
| [Quarkus](https://quarkus.io/)                                          | 3.7     | with [multi project](http://gradle.monochromeroad.com/docs/userguide/multi_project_builds.html) |
| [SmallRye GraphQL Server](https://github.com/smallrye/smallrye-graphql) | 3.7     |                                                                                                 |
| [Picocli](https://picocli.info/)                                        | 3.7     |                                                                                                 |
| [sdkman](https://sdkman.io/)                                            | latest  |                                                                                                 |

## Usage

### Local Environment

#### Running the GraphQL Server in dev mode

```shell
./gradlew clean :graphql-server:quarkusDev
```

#### Running the Batch in dev mode

```shell
# Run batch/src/main/kotlin/com/example/batch/command/HelloCommand.kt
./gradlew clean :batch:quarkusDev --quarkus-args="hello --first-name='Quarkus'"
# Run batch/src/main/kotlin/com/example/batch/command/GoodbyeCommand.kt
./gradlew clean :batch:quarkusDev --quarkus-args="goodbye --name='Quarkus' --times=3"
```

### Production Environment (legacy Jar)

#### Running the GraphQL Server in prod mode

```shell
# [legacy-jar]
## Build
./gradlew clean :graphql-server:build -x test -Dquarkus.package.type=legacy-jar
## Run
java -jar ./graphql-server/build/quarkus-app/quarkus-run.jar

# [uber-jar (Fat Jar)]
## Build
./gradlew clean :graphql-server:build -x test -Dquarkus.package.type=uber-jar
## Run
java -jar ./graphql-server/build/graphql-server-latest-runner.jar
```

#### Running the Batch in prod mode

```shell
# [legacy-jar]
## Build
./gradlew clean :batch:build -x test -Dquarkus.package.type=legacy-jar
## Run batch/src/main/kotlin/com/example/batch/command/HelloCommand.kt
java -jar ./batch/build/quarkus-app/quarkus-run.jar hello --first-name='Quarkus'

# [uber-jar (Fat Jar)]
## Build
./gradlew clean :batch:build -x test -Dquarkus.package.type=uber-jar
## Run batch/src/main/kotlin/com/example/batch/command/GoodbyeCommand.kt
java -jar ./batch/build/batch-latest-runner.jar goodbye --name='Quarkus' --times=3
```

### Production Environment (Native)

#### Install native-image command

Install [sdkman](https://sdkman.io/).

```shell
sdk list java | grep -i graalvm | grep -i ce | grep 21
 GraalVM CE    |     | 21.0.2       | graalce | installed  | 21.0.2-graalce

sdk install java 21.0.2-graalce
```

#### Creating a GraphQL Server native executable

```sh
# Build
./gradlew clean graphql-server:build -x test -Dquarkus.package.type=native
# Run
./graphql-server/build/graphql-server-latest-runner
```

#### Creating a Batch native executable

```sh
# Build
./gradlew clean :batch:build -x test -Dquarkus.package.type=native
# Run batch/src/main/kotlin/com/example/batch/command/HelloCommand.kt
./batch/build/batch-latest-runner hello --first-name='Quarkus'
# Run batch/src/main/kotlin/com/example/batch/command/GoodbyeCommand.kt
./batch/build/batch-latest-runner goodbye --name='Quarkus' --times=3
```

## TIPS

### What's the empty beans.xml?

`resources/META-INF/beans.xml` will allow you to DI beans that are outside of own project.

Details: [Working with multi-module projects](https://quarkus.io/guides/gradle-tooling#multi-module-gradle) .
