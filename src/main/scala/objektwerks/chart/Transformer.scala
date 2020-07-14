package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

import scala.io.{Codec, Source}

object Transformer {
  val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String): List[Glucose] = {
    val lines = csvToLines(path)
    println(lines)
    List.empty[Glucose]
  }

  def csvToMeds(path: String): List[Med] = {
    val lines = csvToLines(path)
    println(lines)
    List.empty[Med]
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