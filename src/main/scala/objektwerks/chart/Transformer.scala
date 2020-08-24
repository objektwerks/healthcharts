package objektwerks.chart

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

import Logger._
import Validator._

object Transformer {
  private val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String, delimiter: String = ","): Try[Glucoses] =
    Try {
      val lines = mutable.ArrayBuilder.make[Glucose]
      val invalidLines = mutable.ArrayBuilder.make[InvalidLine]
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        val columns = line.split(delimiter).map(_.trim)
        validate[Glucose](columns) match {
          case Success(glucose) => lines += glucose
          case Failure(invalidLine) => invalidLines += InvalidLine(line, invalidLine)
        }
      }
      source.close()
      val (linesResult, invalidLinesResult) = (lines.result(), invalidLines.result())
      logLinesAndInvalidLines(linesResult, invalidLinesResult)
      Glucoses(linesResult, invalidLinesResult)
    }

  def csvToMeds(path: String, delimiter: String = ","): Try[Meds] =
    Try {
      val lines = mutable.ArrayBuilder.make[Med]
      val invalidLines = mutable.ArrayBuilder.make[InvalidLine]
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        val columns = line.split(delimiter).map(_.trim)
        validate[Med](columns) match {
          case Success(med) => lines += med
          case Failure(invalidLine) => invalidLines += InvalidLine(line, invalidLine)
        }
      }
      source.close()
      val (linesResult, invalidLinesResult) = (lines.result(), invalidLines.result())
      logLinesAndInvalidLines(linesResult, invalidLinesResult)
      Meds(linesResult, invalidLinesResult)
    }
}