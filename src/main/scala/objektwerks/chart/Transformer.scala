package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.util.Try

object Transformer {
  val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String): Try[Array[Glucose]] =
    Try {
      val buffer = mutable.ArrayBuffer[Glucose]()
      val source = Source.fromFile(path, utf8)
      println(s"transforming: $path")
      for (line <- source.getLines) {
        val columns = line.split(",").map(_.trim)
        if (columns.length == 2) {
          val glucose = Glucose(
            datetime = datetimeToMinute(columns(0)),
            level = columns(1).toInt
          )
          buffer += glucose
          println(line)
        } else println(s"error: $line")
      }
      buffer.toArray
    }

  def csvToMeds(path: String): Try[Array[Med]] =
    Try {
      val buffer = mutable.ArrayBuffer[Med]()
      val source = Source.fromFile(path, utf8)
      println(s"transforming: $path")
      for (line <- source.getLines) {
        val columns = line.split(",").map(_.trim)
        if (columns.length == 3) {
          val med = Med(
            datetime = datetimeToMinute(columns(0)),
            typeof = MedType.map(columns(1).toInt),
            dosage = columns(2).toInt
          )
          buffer += med
          println(line)
        } else println(s"error: $line")
      }
      buffer.toArray
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