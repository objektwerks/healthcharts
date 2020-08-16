package objektwerks.chart

import org.slf4j.LoggerFactory

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

object Transformer {
  private val logger = LoggerFactory.getLogger(GlucoseMedsChart.getClass)
  private val utf8 = Codec.UTF8.name

  def csvToGlucose(path: String, delimiter: String = ","): Try[(Array[Glucose], Array[InvalidLine])] =
    Try {
      val lines = mutable.ArrayBuilder.make[Glucose]
      val invalidLines = mutable.ArrayBuilder.make[InvalidLine]
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        val columns = line.split(delimiter).map(_.trim)
        Glucose.validate(columns) match {
          case Success(glucose) => lines += glucose
          case Failure(invalidLine) => invalidLines += InvalidLine(line, invalidLine)
        }
      }
      source.close()
      val linesAndInvalidLines = (lines.result(), invalidLines.result())
      logLinesAndInvalidLines(linesAndInvalidLines)
      linesAndInvalidLines
    }

  def csvToMeds(path: String, delimiter: String = ","): Try[(Array[Med], Array[InvalidLine])] =
    Try {
      val lines = mutable.ArrayBuilder.make[Med]
      val invalidLines = mutable.ArrayBuilder.make[InvalidLine]
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        val columns = line.split(delimiter).map(_.trim)
        Med.validate(columns) match {
          case Success(med) => lines += med
          case Failure(invalidLine) => invalidLines += InvalidLine(line, invalidLine)
        }
      }
      source.close()
      val linesAndInvalidLines = (lines.result(), invalidLines.result())
      logLinesAndInvalidLines(linesAndInvalidLines)
      linesAndInvalidLines
    }

  def logLinesAndInvalidLines[L, IL](linesAndInvalidLines: (Array[L], Array[IL])): Unit = {
    val (lines, invalidLines) = linesAndInvalidLines
    logger.info(s"lines [${lines.length}]: ${lines.toList.map(g => "\n" + g.toString)}")
    logger.info(s"errors [${invalidLines.length}]: ${invalidLines.toList.map(g => "\n" + g.toString)}")
  }

  def logIOFailure(failure: Throwable, path: String): Unit = logger.error(s"Failed to load $path: ${failure.getMessage}")
}