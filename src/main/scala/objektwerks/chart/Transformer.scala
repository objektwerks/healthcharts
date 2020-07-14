package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.util.Try

object Transformer {
  val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String): Try[List[Glucose]] =
    Try {
      val buffer = mutable.ArrayBuffer[Glucose]()
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        println(line)
        val columns = line.split(",").map(_.trim)
        val glucose = Glucose(
          datetime = datetimeToMinute(columns(0)),
          level = columns(1).toInt
        )
        buffer.addOne( glucose )
      }
      buffer.toList
    }

  def csvToMeds(path: String): Try[List[Med]] =
    Try {
      val buffer = mutable.ArrayBuffer[Med]()
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        println(line)
        val columns = line.split(",").map(_.trim)
        val med = Med(
          datetime = datetimeToMinute(columns(0)),
          typeof = MedType.map(columns(1).toInt),
          dosage = columns(2).toInt
        )
        buffer.addOne( med )
      }
      buffer.toList
    }

  def datetimeToMinute(datetime: String): Minute = {
    val localDateTime = LocalDateTime.parse(datetime)
    new Minute(
      localDateTime.getMinute(),
      localDateTime.getHour(),
      localDateTime.getDayOfMonth(),
      localDateTime.getMinute(),
      localDateTime.getYear()
    )
  }
}