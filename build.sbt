enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "2.4-SNAPSHOT"
scalaVersion := "3.3.0-RC5"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "com.formdev" % "flatlaf" % "3.1.1",
    "org.jfree" % "jfreechart" % "1.5.4",
    "com.miglayout" % "miglayout-swing" % "11.1",
    "com.typesafe" % "config" % "1.4.2",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.4.7",
    "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )
}
jlinkIgnoreMissingDependency := JlinkIgnore.everything
run / fork := true // Required to run Swing UI!
