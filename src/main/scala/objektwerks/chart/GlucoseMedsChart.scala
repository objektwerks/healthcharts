package objektwerks.chart

final case class GlucoseMedsChart(glucose: Array[Glucose], meds: Array[Med]) {
  println(glucose.toList.toString)
  println(meds.toList.toString)
 }