import Dependencies._

lazy val commonSettings = Seq(
  organization := "cn.enali",
  scalaVersion := "2.12.6",
  libraryDependencies ++= slf4jDep ++ Seq(
    scalatestDep % Test,
    "org.projectlombok" % "lombok" % versions("lombok")  // for change bytecode in compile time
  ),
  javacOptions ++= Seq(
    "-encoding", "utf-8"  // solove the GBK encode problem
  ),
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "utf-8"
  ),
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
)

lazy val commonLib = (project in file("common-lib"))
  .settings(
    commonSettings,
    name := "common-lib",
    version := "0.0.1",
    description := "some code used by all other example module",
    crossScalaVersions := Seq("2.11.12", "2.12.6")
  )

// commonLib build for scala_2.12, but the bigdata set the scala version 2.11
lazy val bigdataExample = (project in file("bigdata-example"))
//  .dependsOn(commonLib)
  .settings(
    commonSettings,
    name := "bigdata-example",
    version := "0.0.1",
    scalaVersion := "2.11.12", // spark just support 2.11 now
    description := "some example code about spark application",
    libraryDependencies ++= sparkDep.map(_ % "provided") ++ flinkDep.map(_ % "provided")
  )

lazy val libExample = (project in file("lib-example"))
  .dependsOn(commonLib)
  .settings(
    commonSettings,
    name := "lib-example",
    version := "0.0.1",
    description := "some example code about extra lib",
    resolvers += "lightshed-maven" at "http://dl.bintray.com/content/lightshed/maven", // courier email
    libraryDependencies ++= akkaDep ++ jacksonDep ++ Seq(
      guavaDep, nettyDep,
      "org.json4s" %% "json4s-jackson" % "3.5.3",
      "com.beust" % "jcommander" % "1.72", // cmdline arguments parser
      "ch.lightshed" %% "courier" % "0.1.4", // send email
      "io.dropwizard.metrics" % "metrics-core" % "4.0.3"  // metrics
    )
  )

lazy val designPatternExample = (project in file("design-pattern-example"))
  .dependsOn(commonLib)
  .settings(
    commonSettings,
    name := "design-pattern-example",
    version := "0.0.1",
    description := "some example code about design pattern"
  )

lazy val algorithmsExample = (project in file("algorithms-example"))
  .dependsOn(commonLib)
  .settings(
    commonSettings,
    name := "algorithms-example",
    version := "0.0.1",
    description := "some example code about algorithms"
  )

lazy val otherExample = (project in file("other-example"))
  .dependsOn(commonLib)
  .settings(
    commonSettings,
    name := "other-example",
    version := "0.0.1",
    description := "some other example code, not the lib/stdlib, bigdata, algos"
  )

lazy val root = (project in file("."))
  .aggregate(
    commonLib,
    algorithmsExample,
    otherExample,
    designPatternExample,
    bigdataExample,
    libExample
  ).settings(
  commonSettings,
  name := "john enali's code",
  version := "0.0.1",
  description := "collect some example code"
)
