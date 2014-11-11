name              := "jimcy"

version           := "0.1.0-SNAPSHOT"

organization      := "pl.krever.jimcy"

scalaVersion      := "2.11.1"

crossPaths := false

publishMavenStyle := true

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.4.9" % "test",
  "org.projectlombok" % "lombok" % "1.14.8"
)

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"