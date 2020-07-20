package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.collection.immutable.SortedMap
import scala.util.Try

private object Converter {
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

final case class Glucose(datetime: Minute, level: Int)

object Glucose {
  import Converter._

  val columnCount = 2

  def validate(columns: Array[String]): Either[Throwable, Glucose] =
    Try {
      require(columns.length == columnCount, s"column count != $columnCount")

      val datetime = datetimeToMinute(columns(0))

      val level = columns(1).toInt
      require(level >= 0 && level <= 300, s"level not >= 0 and <= 300")

      Glucose(datetime, level)
    }.toEither
}

object MedType extends Enumeration {
  val insulin = Value(1, "insulin")
  val steroid = Value(2, "steroid")
  val idToMedType = SortedMap[Int, Value](insulin.id -> insulin, steroid.id -> steroid)
}

final case class Med(datetime: Minute, medtype: MedType.Value, dosage: Int)

object Med {
  import Converter._
  import MedType._

  val columnCount = 3

  def validate(columns: Array[String]): Either[Throwable, Med] =
    Try {
      require(columns.length == columnCount, s"column count != $columnCount")

      val datetime = datetimeToMinute(columns(0))

      val medtype = idToMedType(columns(1).toInt)

      val dosage = columns(2).toInt
      require(dosage >= 1 && dosage <= 100, "dosage not >= 1 and <= 100")

      Med(datetime, medtype, dosage)
    }.toEither
}