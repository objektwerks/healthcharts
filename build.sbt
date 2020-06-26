name := "medical.chart"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.2"
libraryDependencies ++= {
  Seq(
    "org.jfree" % "jfreechart" % "1.5.0",
    "org.scalatest" %% "scalatest" % "3.2.0" % Test
  )
}