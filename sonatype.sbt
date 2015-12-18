// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "com.github.krever"

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <url>https://github.com/Krever/JIMCy</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/Krever/JIMCy</connection>
      <developerConnection>scm:git:git@github.com:Krever/JIMCy.git</developerConnection>
      <url>github.com/Krever/JIMCy</url>
    </scm>
    <developers>
      <developer>
        <id>krever</id>
        <name>Wojciech Pituła</name>
        <url>https://github.com/Krever</url>
      </developer>
    </developers>
}