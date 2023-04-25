enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "2.0-SNAPSHOT"
scalaVersion := "3.3.0-RC4"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "org.jfree" % "jfreechart" % "1.5.3",
    "com.miglayout" % "miglayout-swing" % "11.0",
    "com.formdev" % "flatlaf" % "3.1.1",
    "com.typesafe" % "config" % "1.4.2",
    "ch.qos.logback" % "logback-classic" % "1.4.6",
    "org.scalatest" %% "scalatest" % "3.2.15" % Test
  )
}
jlinkIgnoreMissingDependency := JlinkIgnore.everything
