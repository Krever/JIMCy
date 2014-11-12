import sbt._

object HelloBuild extends Build {
  lazy val root = Project(id = "jimcy",
    base = file(".")) aggregate(core, java_api)

  lazy val core = Project(id = "jimcy-core",
    base = file("core"))

  lazy val java_api = Project(id = "jimcy-java-api",
    base = file("java-api")) dependsOn core
}