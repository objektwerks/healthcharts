package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.io.{Codec, Source}
import scala.collection.mutable
import scala.util.Try

object Transformer {
  val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String): Try[List[Glucose]] = {
    Try {
      val buffer = mutable.ArrayBuffer[Glucose]()
      val lines = csvToLines(path)
      println(lines)
      lines.foreach { line =>
        val columns = line.split(",").map(_.trim)
        buffer.addOne( Glucose( datetimeToMinute(columns(0)), columns(1).toInt) )
      }
      buffer.toList
    }
  }

  def csvToMeds(path: String): Try[List[Med]] = {
    Try {
      val buffer = mutable.ArrayBuffer[Med]()
      val lines = csvToLines(path)
      println(lines)
      lines.foreach { line =>
        val columns = line.split(",").map(_.trim)
        buffer.addOne( Med( datetimeToMinute(columns(0)), MedType.map(columns(1).toInt), columns(2).toInt) )
      }
      buffer.toList
    }
  }

  def csvToLines(path: String): Array[String] = {
    Source.fromFile(path, utf8).mkString.split("\n")
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