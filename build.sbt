name := "jimcy"

version in ThisBuild := "0.2.0-SNAPSHOT"

organization in ThisBuild := "com.github.krever"

scalaVersion in ThisBuild := "2.11.7"

publishMavenStyle in ThisBuild := true

libraryDependencies in ThisBuild ++= Seq(
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  "org.specs2" %% "specs2-mock" % "2.4.15" % "test"
)

lazy val root = Project(id = "jimcy", base = file(".")).
  aggregate(core, java_api)
  .settings(publishArtifact := false)

lazy val core = Project(id = "jimcy-core",
  base = file("core"))

lazy val java_api = Project(id = "jimcy-java-api",
  base = file("java-api")) dependsOn core