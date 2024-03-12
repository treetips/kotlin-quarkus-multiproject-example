# kotlin-quarkus-multi-project-example

## Features

| Name                                                                    | Version | Note                                                                                            |
|-------------------------------------------------------------------------|---------|-------------------------------------------------------------------------------------------------|
| [Intellij IDEA](https://www.jetbrains.com/idea/)                        | latest  | Required Plugins: [ktfmt](https://plugins.jetbrains.com/plugin/14912-ktfmt)                     |
| [Kotlin](https://kotlinlang.org/)                                       | 1.9     | With Java v21                                                                                   |
| [Gradle](https://gradle.org/)                                           | 8.6     |                                                                                                 |
| [Quarkus](https://quarkus.io/)                                          | 3.8     | with [multi project](http://gradle.monochromeroad.com/docs/userguide/multi_project_builds.html) |
| [SmallRye GraphQL Server](https://github.com/smallrye/smallrye-graphql) | 3.8     |                                                                                                 |
| [Picocli](https://picocli.info/)                                        | 3.7     |                                                                                                 |
| [sdkman](https://sdkman.io/)                                            | latest  |                                                                                                 |

## Setup

Install [sdkman](https://sdkman.io/).

```shell
sdk list java | grep -i graalvm | grep -i ce | grep 21
 GraalVM CE    |     | 21.0.2       | graalce | installed  | 21.0.2-graalce

sdk install java 21.0.2-graalce
```

Set the version of Graal VM to `GRAAL_VM_HOME` in `env.sh` .

## Usage

### Local Environment

```shell
# admin
./run-admin.sh
# batch
./run-hello-batch.sh
./run-goodbye-batch.sh
# restful-api
./run-restfulapi-server.sh
# graphql
./run-graphql-query1.sh
./run-graphql-server.sh
# gRPC
./run-grpc-server.sh
```

### Production Environment (Native)

```shell
# admin
./build-native-for-admin.sh
# batch
./build-native-for-batch.sh
# restful-api
./build-native-for-restfulapi-server.sh
# graphql
./build-native-for-graphql-server.sh
# gRPC
./build-native-for-grpc-server.sh
```

## TIPS

### What's the empty beans.xml?

`resources/META-INF/beans.xml` will allow you to DI beans that are outside of own project.

Details: [Working with multi-module projects](https://quarkus.io/guides/gradle-tooling#multi-module-gradle) .
