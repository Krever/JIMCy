name              := "jimcy-core"

compileOrder := CompileOrder.JavaThenScala

libraryDependencies ++= Seq(
  "org.projectlombok" % "lombok" % "1.14.8",
  "org.specs2" %% "specs2" % "2.4.9" % "test"
)