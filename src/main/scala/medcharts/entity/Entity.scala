package medcharts.entity

import org.jfree.data.time.Minute

import scala.collection.immutable.SortedMap
import scala.reflect.ClassTag

final case class Entities[E](entities: Array[E], invalidLines: Array[InvalidLine])

object Entities {
  def apply[E: ClassTag](entities: Array[E], invalidLines: Array[InvalidLine]): Entities[E] = new Entities[E]( entities, invalidLines )

  def empty[E: ClassTag]: Entities[E] = new Entities[E]( Array.empty[E], Array.empty[InvalidLine] )
}

final case class BloodPressure(datetime: Minute, systolic: Int, diastolic: Int)

final case class Glucose(datetime: Minute, level: Int)

object MedType extends Enumeration {
  val insulin = Value(1, "insulin")
  val steroid = Value(2, "steroid")
  val idToMedType = SortedMap[Int, Value](insulin.id -> insulin, steroid.id -> steroid)
}

final case class Med(datetime: Minute, medtype: MedType.Value, dosage: Int)

final case class Pulse(datetime: Minute, beatsPerMinute: Int)

final case class PulseOxygen(datetime: Minute, beatsPerMinute: Int, bloodOxygenPercentage: Int)

final case class Respiration(datetime: Minute, breathesPerMinute: Int)

final case class Temperature(datetime: Minute, degrees: Double)

final case class Vitals(datetime: Minute,
                        temperature: Double,
                        respiration: Int,
                        pulse: Int,
                        oxygen: Int,
                        systolic: Int,
                        diastolic: Int)

final case class Weight(datetime: Minute, pounds: Double)

final case class InvalidLine(line: String, error: Throwable)