enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "2.5-SNAPSHOT"
scalaVersion := "3.5.0-RC2"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "com.formdev" % "flatlaf" % "3.4.1",
    "org.jfree" % "jfreechart" % "1.5.4",
    "com.miglayout" % "miglayout-swing" % "11.2",
    "com.typesafe" % "config" % "1.4.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
    "ch.qos.logback" % "logback-classic" % "1.5.6",
    "org.scalatest" %% "scalatest" % "3.2.18" % Test
  )
}
scalacOptions ++= Seq(
  "-Wunused:all"
)
jlinkIgnoreMissingDependency := JlinkIgnore.everything
run / fork := true // Required to run Swing UI!
