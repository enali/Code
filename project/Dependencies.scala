import sbt._

object Dependencies {
  lazy val versions = Map(
    "spark" -> "2.3.1",
    "flink" -> "1.5.1",
    "akka" -> "2.5.14",
    "akka-http" -> "10.1.1",
    "jackson" -> "2.9.3",
    "scalatest" -> "3.0.5",
    "lombok" -> "1.18.2",
    "netty" -> "4.1.28.Final",
    "guava" -> "26.0-jre",
    "slf4j" -> "1.8.0-beta2",
    "log4j" -> "2.11.1",
    "scalatest" -> "3.0.5"
  )

  lazy val guavaDep = "com.google.guava" % "guava" % versions("guava")  // google guava

  lazy val nettyDep = "io.netty" % "netty-all" % versions("netty")  // netty

  lazy val slf4jDep = Seq(
    "org.slf4j" % "slf4j-api" % versions("slf4j"),
    "org.slf4j" % "slf4j-log4j12" % versions("slf4j"),  // used with log4j
    "org.apache.logging.log4j" % "log4j-core" % versions("log4j")
//    "org.slf4j" % "slf4j-simple" % versions("slf4j") % Test  // used with simple
  )

  lazy val scalatest = "org.scalatest" %% "scalatest" % versions("scalatest") % "test"

  lazy val akkaDep = Seq(
    "com.typesafe.akka" %% "akka-stream" % versions("akka"),
    "com.typesafe.akka" %% "akka-remote" % versions("akka"),
    "com.typesafe.akka" %% "akka-http" % versions("akka-http"),
    "com.typesafe.akka" %% "akka-http-spray-json" % versions("akka-http")
  )

  lazy val sparkDep = Seq(
    // "% provided"避免assembly时生成过大的包, 从idea中直接运行spark应用时要注意配置
    "org.apache.spark" %% "spark-core" % versions("spark") % "provided",
    "org.apache.spark" %% "spark-sql" % versions("spark") % "provided"
  )

  lazy val flinkDep = Seq(
    "org.apache.flink" %% "flink-scala" % versions("flink") % "provided",
    "org.apache.flink" %% "flink-streaming-scala" % versions("flink") % "provided"
  )

  // serialize/deserialize
  lazy val jacksonDep = Seq(
    "com.fasterxml.jackson.core" % "jackson-core" % versions("jackson"),
    "com.fasterxml.jackson.core" % "jackson-databind" % versions("jackson"),
    "com.fasterxml.jackson.core" % "jackson-annotations" % versions("jackson")
  )
}
