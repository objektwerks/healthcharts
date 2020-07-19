package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.collection.immutable.SortedMap
import scala.util.Try

private object Common {
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

  def isLineValid(line: String, columnCount: Int): Array[String] = {
    val columns = line.split(",").map(_.trim)
    require(columns.length == columnCount, s"columns length != $columnCount")
    columns
  }
}

final case class InvalidLine(line: String, error: Throwable)

object MedType extends Enumeration {
  val insulin = Value(1, "insulin")
  val steroid = Value(2, "steroid")
  val idToMedType = SortedMap[Int, Value](insulin.id -> insulin, steroid.id -> steroid)
}

final case class Med(datetime: Minute, medtype: MedType.Value, dosage: Int)

object Med {
  import Common._
  import MedType._

  def validate(line: String): Either[Throwable, Med] =
    Try {
      val columns = isLineValid(line, 3)

      val datetime = datetimeToMinute(columns(0))

      val medType = idToMedType(columns(1).toInt)

      val dosage = columns(2).toInt
      require(dosage >= 1 && dosage <= 100)

      Med(datetime, medType, dosage)
    }.toEither
}

final case class Glucose(datetime: Minute, level: Int)

object Glucose {
  import Common._

  def validate(line: String): Either[Throwable, Glucose] =
    Try {
      val columns = isLineValid(line, 2)

      val datetime = datetimeToMinute(columns(0))

      val level = columns(1).toInt
      require(level >= 0 && level <= 300)

      Glucose(datetime, level)
    }.toEither
}