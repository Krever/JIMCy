name                       := "jimcy"

version in ThisBuild       := "0.1.0-SNAPSHOT"

organization in ThisBuild  := "pl.krever.jimcy"

scalaVersion in ThisBuild  := "2.11.1"

crossPaths in ThisBuild    := false

publishMavenStyle in ThisBuild := true

libraryDependencies in ThisBuild ++= Seq(
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  "org.specs2" %% "specs2-mock" % "2.4.15" % "test"
)


scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"