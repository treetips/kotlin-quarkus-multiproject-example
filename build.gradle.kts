val quarkusPlatformGroupId: String by project

val quarkusPlatformArtifactId: String by project

val quarkusPlatformVersion: String by project

val projectGroup: String by project

val restAssuredVersion: String by project

plugins {
  kotlin("jvm") version "1.4.31"
  id("org.jetbrains.kotlin.plugin.allopen") version "1.4.31"
  id("io.quarkus")
}

repositories { mavenCentral() }

subprojects {
  apply {
    plugin("java-library")
    plugin("org.jetbrains.kotlin.jvm")
    plugin("org.jetbrains.kotlin.plugin.allopen")
    plugin("io.quarkus")
  }

  repositories {
    mavenLocal()
    mavenCentral()
  }

  group = projectGroup
  version = "latest"

  java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  dependencies {
    implementation(
        enforcedPlatform(
            "${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-config-yaml")

    testImplementation("io.quarkus:quarkus-junit5")
  }

  allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
  }

  tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
        javaParameters = true
      }
    }
    withType<Test> { useJUnitPlatform() }
  }
}

project(":share") { dependencies {} }

project(":graphql-server") {
  dependencies {
    implementation("io.quarkus:quarkus-smallrye-graphql")
    implementation("io.quarkus:quarkus-reactive-mysql-client")
    testImplementation("io.rest-assured:rest-assured:${restAssuredVersion}")
  }
}

project(":batch") {
  dependencies {
    implementation(project(":share"))
    implementation("io.quarkus:quarkus-picocli")
  }
}

/** @see https://docs.gradle.org/current/userguide/gradle_wrapper.html#customizing_wrapper */
tasks.named<Wrapper>("wrapper") {
  gradleVersion = "6.8.3"
  distributionType = Wrapper.DistributionType.BIN
}
