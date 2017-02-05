lazy val root = (project in file(".")).
  settings(
    name := "app",
    version := "1.0",
    scalaVersion := "2.11.4",
    mainClass in Compile := Some("com.droletours.boot")
  )

organization  := "com.droletours"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "io.spray"            %%  "spray-client"  % sprayV,
    "io.spray"            %%  "spray-json"    % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
  )
}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

Revolver.settings
