lazy val commonSettings = Seq(
  organization := "cn.enali",
  scalaVersion := "2.12.6",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  ),
  javacOptions ++= Seq(
    "-encoding", "UTF-8"
  ),
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
)

lazy val bigdataExample = (project in file("bigdata-example"))
    .settings(
      commonSettings,
      name := "bigdata-example",
      version := "0.0.1",
      scalaVersion := "2.11.12",  // spark just support 2.11 now
      description := "some example code about spark application",
      libraryDependencies ++= Seq(
        // spark
        "org.apache.spark" %% "spark-core" % "2.3.1" % "provided",
        "org.apache.spark" %% "spark-sql" % "2.3.1" % "provided"
      )
    )

lazy val libExample = (project in file("lib-example"))
    .settings(
      commonSettings,
      name := "lib-example",
      version := "0.0.1",
      description := "some example code about extra lib",
      resolvers += "lightshed-maven" at "http://dl.bintray.com/content/lightshed/maven",  // courier email
      libraryDependencies ++= Seq(
        "com.fasterxml.jackson.core" % "jackson-core" % "2.9.3",  // serialize/deserialize
        "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.3",
        "com.fasterxml.jackson.core" % "jackson-annotations" % "2.9.3",
        "org.json4s" %% "json4s-jackson" % "3.5.3",
        "com.beust" % "jcommander" % "1.72",  // cmdline arguments parser
        "ch.lightshed" %% "courier" % "0.1.4",  // send email
        "com.typesafe.akka" %% "akka-http" % "10.1.1",  // akka actor
        "com.typesafe.akka" %% "akka-stream" % "2.5.14",
        "com.typesafe.akka" %% "akka-remote" % "2.5.14",
        "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.1",
      )
    )

lazy val commonExample = (project in file("common-example"))
  .settings(
    commonSettings,
    name := "common-example",
    version := "0.0.1",
    description := "some example code"
  )

lazy val designPatternExample = (project in file("design-pattern-example"))
  .settings(
    commonSettings,
    name := "design-pattern-example",
    version := "0.0.1",
    description := "some example code about design pattern"
  )

lazy val algorithmsExample = (project in file("algorithms-example"))
  .settings(
    commonSettings,
    name := "algorithms-example",
    version := "0.0.1",
    description := "some example code about algorithms"
  )

lazy val root = (project in file("."))
  .aggregate(
    algorithmsExample,
    commonExample,
    designPatternExample,
    bigdataExample,
    libExample
  ).settings(
  commonSettings,
  name := "john enali's code",
  version := "0.0.1",
  description := "collect some example code"
)


