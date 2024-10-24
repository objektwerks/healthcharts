enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "2.5-SNAPSHOT"
scalaVersion := "3.6.1"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "com.formdev" % "flatlaf" % "3.5.2",
    "org.jfree" % "jfreechart" % "1.5.4",
    "com.miglayout" % "miglayout-swing" % "11.2",
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.11",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wall"
)
jlinkIgnoreMissingDependency := JlinkIgnore.everything
run / fork := true // Required to run Swing UI!
