enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "1.3-SNAPSHOT"
scalaVersion := "3.0.0-M2"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "org.jfree" % "jfreechart" % "1.5.1",
    "com.miglayout" % "miglayout-swing" % "5.2",
    "com.typesafe" % "config" % "1.4.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalameta" %% "munit" % "0.7.19" % Test
  )
}
testFrameworks += new TestFramework("munit.Framework")
parallelExecution in Test := false
semanticdbEnabled := true
jlinkModules := {
  jlinkModules.value :+ "jdk.unsupported"
}
jlinkIgnoreMissingDependency := JlinkIgnore.everything