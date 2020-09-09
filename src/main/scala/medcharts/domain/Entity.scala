package medcharts.domain

import org.jfree.data.time.Minute

import scala.collection.immutable.SortedMap

final case class Glucose(datetime: Minute, level: Int)

final case class Glucoses(lines: Array[Glucose], invalidLines: Array[InvalidLine])

object Glucoses {
  def empty: Glucoses = Glucoses(Array.empty[Glucose], Array.empty[InvalidLine])
}

object MedType extends Enumeration {
  val insulin = Value(1, "insulin")
  val steroid = Value(2, "steroid")
  val idToMedType = SortedMap[Int, Value](insulin.id -> insulin, steroid.id -> steroid)
}

final case class Med(datetime: Minute, medtype: MedType.Value, dosage: Int)

final case class Meds(lines: Array[Med], invalidLines: Array[InvalidLine])

object Meds {
  def empty: Meds = Meds(Array.empty[Med], Array.empty[InvalidLine])
}

final case class Weight(datetime: Minute, lbs: Double)

final case class InvalidLine(line: String, error: Throwable)