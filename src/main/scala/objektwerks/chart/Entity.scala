package objektwerks.chart

import org.jfree.data.time.Minute
import scala.collection.immutable.SortedMap

object MedType extends Enumeration {
  val Insulin = Value(1, "Insulin")
  val Steroid = Value(2, "Steroid")
  val map = SortedMap[Int, Value](Insulin.id -> Insulin, Steroid.id -> Steroid)
  def validate(medType: MedType.Value): Boolean = values.contains(medType)
}

final case class Med(datetime: Minute, medType: MedType.Value, dosage: Int)

final case class Glucose(datetime: Minute, level: Int)