import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.octopus",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "octopus",
    libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.0",
    libraryDependencies += "com.softwaremill.sttp" %% "core" % "1.3.5",
    libraryDependencies += "com.softwaremill.sttp" %% "akka-http-backend" % "1.3.5",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.11",
    libraryDependencies += scalaTest % Test,
  )

mainClass in assembly := Some("com.octopus.Octopus")

assemblyJarName in assembly := s"${name.value}.jar"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case "reference.conf" => MergeStrategy.concat
  case x => MergeStrategy.first
}