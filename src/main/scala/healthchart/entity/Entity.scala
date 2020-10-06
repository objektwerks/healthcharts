package healthchart.entity

import org.jfree.data.time.Minute

import scala.collection.immutable.SortedMap
import scala.reflect.ClassTag

sealed trait Entity extends Product with Serializable {
  val number: Int
  val datetime: Minute
}

final case class Entities[E](entities: Array[E], invalidLines: Array[InvalidLine]) extends Product with Serializable {
  def toEntity: Array[Entity] = entities.asInstanceOf[Array[Entity]]
}

object Entities {
  def apply[E: ClassTag](entities: Array[E], invalidLines: Array[InvalidLine]): Entities[E] = new Entities[E]( entities, invalidLines )

  def empty[E: ClassTag]: Entities[E] = new Entities[E]( Array.empty[E], Array.empty[InvalidLine] )
}

final case class BloodPressure(number: Int, datetime: Minute, systolic: Int, diastolic: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $systolic, $diastolic"
}

final case class Glucose(number: Int, datetime: Minute, level: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $level"
}

object MedType extends Enumeration {
  val insulin = Value(1, "insulin")
  val steroid = Value(2, "steroid")
  val idToMedType = SortedMap[Int, Value](insulin.id -> insulin, steroid.id -> steroid)
}

final case class Med(number: Int, datetime: Minute, medtype: MedType.Value, dosage: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $medtype, $dosage"
}

final case class GlucoseMed(number: Int, datetime: Minute, level: Int, medtype: MedType.Value, dosage: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $level, $medtype, $dosage"
}

final case class Pulse(number: Int, datetime: Minute, beatsPerMinute: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $beatsPerMinute"
}

final case class PulseOxygen(number: Int, datetime: Minute, beatsPerMinute: Int, bloodOxygenPercentage: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $beatsPerMinute, $bloodOxygenPercentage"
}

final case class Respiration(number: Int, datetime: Minute, breathesPerMinute: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $breathesPerMinute"
}

final case class Temperature(number: Int, datetime: Minute, degrees: Double) extends Entity {
  override def toString: String = s"$number, $datetime, $degrees"
}

final case class Vitals(number: Int,
                        datetime: Minute,
                        temperature: Double,
                        respiration: Int,
                        pulse: Int,
                        oxygen: Int,
                        systolic: Int,
                        diastolic: Int) extends Entity {
  override def toString: String = s"$number, $datetime, $temperature, $respiration, $pulse, $oxygen, $systolic, $diastolic"
}

final case class Weight(number: Int, datetime: Minute, pounds: Double) extends Entity {
  override def toString: String = s"$number, $datetime, $pounds"
}

final case class InvalidLine(number: Int, line: String, error: Throwable) extends Product with Serializable {
  /** Error getMessage starts with: 'requirement failed: ' The substring(20) cuts it out. */
  override def toString: String = s"$number, ${error.getMessage.substring(20)}, $line"
}