val scala213Version = "2.13.6"
val AkkaVersion = "2.6.14"
val AkkaHttpVersion = "10.2.4"

lazy val root = project
  .in(file("."))
  .settings(
    organization := "net.jmlamo",
    name := "tdd-demo-swcraftlyon",
    version := "0.1.0",
    scalaVersion := scala213Version,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.scalatest" %% "scalatest" % "3.2.9" % Test,
      "org.mockito" %% "mockito-scala" % "1.16.37" % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % AkkaVersion % Test,
      "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion % Test,
      "com.stephenn" %% "scalatest-json-jsonassert" % "0.2.0" % Test
    )
  )
