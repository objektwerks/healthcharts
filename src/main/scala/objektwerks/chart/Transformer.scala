package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.util.Try

object Transformer {
  val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String): Try[(Array[Glucose], Array[InvalidLine])] =
    Try {
      val lines = mutable.ArrayBuffer[Glucose]()
      val errors = mutable.ArrayBuffer[InvalidLine]()
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        val columns = line.split(",").map(_.trim)
        if (columns.length == 2) {
          val glucose = Glucose(
            datetime = datetimeToMinute(columns(0)),
            level = columns(1).toInt
          )
          lines += glucose
        } else errors += InvalidLine(line)
      }
      (lines.toArray, errors.toArray)
    }

  def csvToMeds(path: String): Try[(Array[Med], Array[InvalidLine])] =
    Try {
      val lines = mutable.ArrayBuffer[Med]()
      val errors = mutable.ArrayBuffer[InvalidLine]()
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        val columns = line.split(",").map(_.trim)
        if (columns.length == 3) {
          val med = Med(
            datetime = datetimeToMinute(columns(0)),
            typeof = MedType.map(columns(1).toInt),
            dosage = columns(2).toInt
          )
          lines += med
        } else errors += InvalidLine(line)
      }
      (lines.toArray, errors.toArray)
    }

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