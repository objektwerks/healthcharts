package objektwerks.chart

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
        Glucose.validate(columns) match {
          case Left(error) => errors += InvalidLine(line, error)
          case Right(glucose) => lines += glucose
        }
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
        Med.validate(columns) match {
          case Left(error) => errors += InvalidLine(line, error)
          case Right(med) => lines += med
        }
      }
      (lines.toArray, errors.toArray)
    }
}