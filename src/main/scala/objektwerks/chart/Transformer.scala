package objektwerks.chart

import scala.collection.mutable.ArrayBuilder
import scala.io.{Codec, Source}
import scala.util.Try

final case class InvalidLine(line: String, error: Throwable)

object Transformer {
  private val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String, delimiter: String = ","): Try[(Array[Glucose], Array[InvalidLine])] =
    Try {
      val lines = ArrayBuilder.make[Glucose]
      val errors = ArrayBuilder.make[InvalidLine]
      for (line <- Source.fromFile(path, utf8).getLines) {
        val columns = line.split(delimiter).map(_.trim)
        Glucose.validate(columns) match {
          case Left(error) => errors += InvalidLine(line, error)
          case Right(glucose) => lines += glucose
        }
      }
      (lines.result(), errors.result())
    }

  def csvToMeds(path: String, delimiter: String = ","): Try[(Array[Med], Array[InvalidLine])] =
    Try {
      val lines = ArrayBuilder.make[Med]
      val errors = ArrayBuilder.make[InvalidLine]
      for (line <- Source.fromFile(path, utf8).getLines) {
        val columns = line.split(delimiter).map(_.trim)
        Med.validate(columns) match {
          case Left(error) => errors += InvalidLine(line, error)
          case Right(med) => lines += med
        }
      }
      (lines.result(), errors.result())
    }
}