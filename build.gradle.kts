val quarkusPlatformGroupId: String by project

val quarkusPlatformArtifactId: String by project

val quarkusPlatformVersion: String by project

val projectGroup: String by project

val restAssuredVersion: String by project

plugins {
  kotlin("jvm") version "1.9.22"
  kotlin("plugin.allopen") version "1.9.22"
  id("io.quarkus")
  id("idea")
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
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
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
        jvmTarget = JavaVersion.VERSION_21.toString()
        javaParameters = true
      }
    }
    withType<Test> { useJUnitPlatform() }
  }
}

project(":share") { dependencies {} }

project(":admin") {
  dependencies {
    implementation(project(":share"))
    implementation("io.quarkus:quarkus-resteasy-reactive-qute")
  }
}

project(":graphql-server") {
  dependencies {
    implementation("io.quarkus:quarkus-smallrye-graphql")
    testImplementation("io.rest-assured:rest-assured:${restAssuredVersion}")
  }
}

project(":restfulapi-server") {
  dependencies {
    implementation(project(":share"))
    implementation(
        enforcedPlatform("${quarkusPlatformGroupId}:quarkus-camel-bom:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("org.apache.camel.quarkus:camel-quarkus-bean-validator")
  }
}

project(":batch") {
  dependencies {
    implementation(project(":share"))
    implementation("io.quarkus:quarkus-picocli")
  }
}

idea {
  module {
    isDownloadJavadoc = false
    isDownloadSources = true
  }
}

/** @see https://docs.gradle.org/current/userguide/gradle_wrapper.html#customizing_wrapper */
tasks.named<Wrapper>("wrapper") {
  gradleVersion = "8.6"
  distributionType = Wrapper.DistributionType.BIN
}
