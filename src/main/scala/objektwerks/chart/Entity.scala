package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.collection.immutable.SortedMap
import scala.util.Try

trait Common {
    def datetimeToMinute(datetime: String): Minute = {
    val localDateTime = LocalDateTime.parse(datetime)
    new Minute(
      localDateTime.getMinute(),
      localDateTime.getHour(),
      localDateTime.getDayOfMonth(),
      localDateTime.getMonthValue(),
      localDateTime.getYear()
    )
  }
}

final case class InvalidLine(line: String, error: Throwable)

object MedType extends Enumeration {
  val Insulin = Value(1, "Insulin")
  val Steroid = Value(2, "Steroid")
  val map = SortedMap[Int, Value](Insulin.id -> Insulin, Steroid.id -> Steroid)
  def validate(medType: MedType.Value): Boolean = values.contains(medType)
}

final case class Med(datetime: Minute, typeof: MedType.Value, dosage: Int)

object Med extends Common {
  def validate(columns: Array[String]): Either[Throwable, Med] = Try {
    require(columns.length == 3)
    val datetime = datetimeToMinute(columns(0))
    val typeof = MedType.map(columns(1).toInt)
    val dosage = columns(2).toInt
    Med(datetime, typeof, dosage)
  }.toEither
}

final case class Glucose(datetime: Minute, level: Int)

object Glucose extends Common {
  def validate(columns: Array[String]): Either[Throwable, Glucose] = Try {
    require(columns.length == 2)
    val datetime = datetimeToMinute(columns(0))
    val level = columns(1).toInt
    Glucose(datetime, level)
  }.toEither
}