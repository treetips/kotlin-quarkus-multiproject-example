# kotlin-quarkus-multi-project-example

## Features

* [Kotlin](https://kotlinlang.org/) (Java v11)
* [Gradle](https://gradle.org/)
* [Quarkus](https://quarkus.io/)
  with [multi project](http://gradle.monochromeroad.com/docs/userguide/multi_project_builds.html)
* [SmallRye GraphQL Server](https://github.com/smallrye/smallrye-graphql)
* [Picocli](https://picocli.info/)
* [jq](https://stedolan.github.io/jq/)
* [Intellij IDEA](https://www.jetbrains.com/idea/)
  * Required Plugins
    * [kotlin](https://plugins.jetbrains.com/plugin/6954-kotlin)
    * [Save Actions](https://plugins.jetbrains.com/plugin/7642-save-actions)
    * [ktfmt](https://plugins.jetbrains.com/plugin/14912-ktfmt)
* Package Manager
  * Need one of the following
    * [Homebrew](https://brew.sh/)
    * [sdkman](https://sdkman.io/) (Recommended)

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

### Production Environment (Fast Jar)

#### Running the GraphQL Server in prod mode

```shell
# [fast-jar]
## Build
./gradlew clean :graphql-server:build -Dquarkus.package.type=fast-jar
## Run
java -jar ./graphql-server/build/quarkus-app/quarkus-run.jar

# [uber-jar (Fat Jar)]
## Build
./gradlew clean :graphql-server:build -Dquarkus.package.type=uber-jar
## Run
java -jar ./graphql-server/build/graphql-server-latest-runner.jar
```

#### Running the Batch in prod mode

```shell
# [fast-jar]
## Build
./gradlew clean :batch:build -Dquarkus.package.type=fast-jar
## Run batch/src/main/kotlin/com/example/batch/command/HelloCommand.kt
java -jar ./batch/build/quarkus-app/quarkus-run.jar hello --first-name='Quarkus'

# [uber-jar (Fat Jar)]
## Build
./gradlew clean :batch:build -Dquarkus.package.type=uber-jar
## Run batch/src/main/kotlin/com/example/batch/command/GoodbyeCommand.kt
java -jar ./batch/build/batch-latest-runner.jar goodbye --name='Quarkus' --times=3
```

### Production Environment (Native)

#### Install native-image command

##### For Homebrew on macOS

Install [Homebrew](https://brew.sh/).

```sh
# @see https://github.com/graalvm/homebrew-tap
brew install --cask graalvm/tap/graalvm-ce-lts-java11
export GRAALVM_HOME=`/usr/libexec/java_home -v GraalVM Community`
export PATH=$PATH:$GRAALVM_HOME/bin
gu install native-image
```

##### For sdkman on sdkman

Install [sdkman](https://sdkman.io/).

```shell
sdk list java GraalVM | grep grl | grep r11
 GraalVM       |     | 21.0.0.2.r11 | grl     |            | 21.0.0.2.r11-grl
               |     | 20.3.1.2.r11 | grl     |            | 20.3.1.2.r11-grl
               |     | 19.3.5.r11   | grl     |            | 19.3.5.r11-grl

sdk install java 21.0.0.2.r11-grl
export GRAALVM_HOME=$HOME/.sdkman/candidates/java/21.0.0.2.r11-grl
export PATH=$PATH:$GRAALVM_HOME/bin
gu install native-image
```

#### Creating a GraphQL Server native executable

```sh
# Build
./gradlew clean :graphql-server:build -Dquarkus.package.type=native
# Run
./graphql-server/build/graphql-server-latest-runner
```

#### Creating a Batch native executable

```sh
# Build
./gradlew clean :batch:build -Dquarkus.package.type=native
# Run batch/src/main/kotlin/com/example/batch/command/HelloCommand.kt
./batch/build/batch-latest-runner hello --first-name='Quarkus'
# Run batch/src/main/kotlin/com/example/batch/command/GoodbyeCommand.kt
./batch/build/batch-latest-runner goodbye --name='Quarkus' --times=3
```

## TIPS

### What's the empty beans.xml?

`resources/META-INF/beans.xml` will allow you to DI beans that are outside of own project.

Details: [Working with multi-module projects](https://quarkus.io/guides/gradle-tooling#multi-module-gradle) .
