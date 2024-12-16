enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "2.5-SNAPSHOT"
scalaVersion := "3.6.3-RC1"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "com.formdev" % "flatlaf" % "3.5.4",
    "org.jfree" % "jfreechart" % "1.5.5",
    "com.miglayout" % "miglayout-swing" % "11.3",
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.12",
    "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
}
scalacOptions ++= Seq(
  "-Wall"
)
jlinkIgnoreMissingDependency := JlinkIgnore.everything
run / fork := true // Required to run Swing UI!
