enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "2.0"
scalaVersion := "3.1.2"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "org.jfree" % "jfreechart" % "1.5.3",
    "com.miglayout" % "miglayout-swing" % "11.0",
    "com.typesafe" % "config" % "1.4.2",
    "ch.qos.logback" % "logback-classic" % "1.2.11",
    "org.scalatest" %% "scalatest" % "3.2.11" % Test
  )
}
