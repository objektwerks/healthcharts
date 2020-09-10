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

final case class Weight(datetime: Minute, pounds: Double)

final case class Weights(lines: Array[Weight], invalidLines: Array[InvalidLine])

object Weights {
  def empty: Weights = Weights(Array.empty[Weight], Array.empty[InvalidLine])
}

final case class Pulse(datetime: Minute, beatsPerMinute: Int)

final case class Pulses(lines: Array[Pulse], invalidLines: Array[InvalidLine])

object Pulses {
  def empty: Pulses = Pulses(Array.empty[Pulse], Array.empty[InvalidLine])
}

final case class PulseOxygen(datetime: Minute, beatsPerMinute: Int, bloodOxygenPercentage: Int)

final case class PulseOxygens(lines: Array[PulseOxygen], invalidLines: Array[InvalidLine])

object PulseOxygens {
  def empty: PulseOxygens = PulseOxygens(Array.empty[PulseOxygen], Array.empty[InvalidLine])
}

final case class Respiration(datetime: Minute, breathesPerMinute: Int)

final case class Respirations(lines: Array[Respiration], invalidLines: Array[InvalidLine])

object Respirations {
  def empty: Respirations = Respirations(Array.empty[Respiration], Array.empty[InvalidLine])
}

final case class Temperature(datetime: Minute, degrees: Double)

final case class Temperatures(lines: Array[Temperature], invalidLines: Array[InvalidLine])

object Temperatures {
  def empty: Temperatures = Temperatures(Array.empty[Temperature], Array.empty[InvalidLine])
}

final case class BloodPressure(datetime: Minute, systolic: Int, diastolic: Int)

final case class BloodPressures(lines: Array[BloodPressure], invalidLines: Array[InvalidLine])

object BloodPressures {
  def empty: BloodPressures = BloodPressures(Array.empty[BloodPressure], Array.empty[InvalidLine])
}

final case class InvalidLine(line: String, error: Throwable)