enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "1.9-SNAPSHOT"
scalaVersion := "2.13.8"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "org.jfree" % "jfreechart" % "1.5.3",
    "com.miglayout" % "miglayout-swing" % "11.0",
    "com.typesafe" % "config" % "1.4.2",
    "ch.qos.logback" % "logback-classic" % "1.2.11",
    "org.scalameta" %% "munit" % "0.7.29" % Test
  )
}
testFrameworks += new TestFramework("munit.Framework")
parallelExecution in Test := false
