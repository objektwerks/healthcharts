enablePlugins(JlinkPlugin)

name := "healthcharts"
organization := "objektwerks"
version := "1.8-SNAPSHOT"
scalaVersion := "2.13.4"
maintainer := "objektwerks@runbox.com"
libraryDependencies ++= {
  Seq(
    "org.jfree" % "jfreechart" % "1.5.2",
    "com.miglayout" % "miglayout-swing" % "5.2",
    "com.typesafe" % "config" % "1.4.0",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "org.scalameta" %% "munit" % "0.7.22" % Test
  )
}
testFrameworks += new TestFramework("munit.Framework")
parallelExecution in Test := false
semanticdbEnabled := true
jlinkModules := {
  jlinkModules.value :+ "jdk.unsupported"
}
jlinkIgnoreMissingDependency := JlinkIgnore.everything
