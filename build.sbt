enablePlugins(JlinkPlugin)

name := "medcharts"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.2"
libraryDependencies ++= {
  Seq(
    "org.jfree" % "jfreechart" % "1.5.0",
    "com.miglayout" % "miglayout-swing" % "5.2",
    "com.typesafe" % "config" % "1.4.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalatest" %% "scalatest" % "3.2.0" % Test
  )
}
jlinkModules := {
  jlinkModules.value :+ "jdk.unsupported"
}
jlinkIgnoreMissingDependency := JlinkIgnore.everything