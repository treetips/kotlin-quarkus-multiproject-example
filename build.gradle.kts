val projectGroup: String by project

plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.plugin.allopen)
  alias(libs.plugins.io.quarkus)
  idea
}

repositories {
  mavenLocal()
  mavenCentral()
  gradlePluginPortal()
}

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
    implementation(enforcedPlatform(rootProject.libs.quarkus.bom))
    implementation(rootProject.libs.bundles.common.project.bundles)

    testImplementation(rootProject.libs.quarkus.junit5)
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
    implementation(rootProject.libs.bundles.admin.main)
  }
}

project(":graphql-server") {
  dependencies {
    implementation(rootProject.libs.bundles.graphql.server.main)
    testImplementation(rootProject.libs.bundles.graphql.server.test)
  }
}

project(":grpc-server") {
  dependencies { implementation(rootProject.libs.bundles.grpc.server.main) }
}

project(":restfulapi-server") {
  dependencies {
    implementation(project(":share"))
    implementation(enforcedPlatform(rootProject.libs.quarkus.camel.bom))
    implementation(rootProject.libs.bundles.restfulapi.server.main)
  }
}

project(":batch") {
  dependencies {
    implementation(project(":share"))
    implementation(rootProject.libs.bundles.batch.main)
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
  gradleVersion = "8.7"
  distributionType = Wrapper.DistributionType.BIN
}
