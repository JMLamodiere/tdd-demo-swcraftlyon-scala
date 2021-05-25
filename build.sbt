val scala213Version = "2.13.6"

lazy val root = project
  .in(file("."))
  .settings(
    organization := "net.jmlamo",
    name := "tdd-demo-swcraftlyon",
    version := "0.1.0",

    scalaVersion := scala213Version,

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.9" % Test,
      "org.mockito" %% "mockito-scala" % "1.16.37" % Test
    )
  )
