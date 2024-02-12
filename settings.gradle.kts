pluginManagement {
  val quarkusPluginVersion: String by settings
  val quarkusPluginId: String by settings
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
  }
  plugins { id(quarkusPluginId) version quarkusPluginVersion }
}

rootProject.name = "kotlin-quarkus-multiproject-example"

include("share", "batch", "graphql-server", "restfulapi-server")
