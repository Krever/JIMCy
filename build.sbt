name := "JIMCy"

version := "1.0"


libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.4.9" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"