package medcharts.domain

import medcharts.domain.Logger._
import medcharts.domain.Validator._

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

trait Transformer[E] {
  def transform(path: String, delimiter: String = ","): Try[E]
}

object Transformer {
  private val utf8 = Codec.UTF8.name

  def transform[E](path: String, delimiter: String = ",")(implicit transformer: Transformer[E]): Try[E] = 
    transformer.transform(path, delimiter)

  implicit object GlucosesTransformer extends Transformer[Glucoses] {
    def transform(path: String, delimiter: String = ","): Try[Glucoses] =
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
  }

  implicit object MedsTransformer extends Transformer[Meds] {
    def transform(path: String, delimiter: String = ","): Try[Meds] =
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

  implicit object WeightsTransformer extends Transformer[Weights] {
    def transform(path: String, delimiter: String = ","): Try[Weights] =
      Try {
        val lines = mutable.ArrayBuilder.make[Weight]
        val invalidLines = mutable.ArrayBuilder.make[InvalidLine]
        val source = Source.fromFile(path, utf8)
        for (line <- source.getLines) {
          val columns = line.split(delimiter).map(_.trim)
          validate[Weight](columns) match {
            case Success(weight) => lines += weight
            case Failure(invalidLine) => invalidLines += InvalidLine(line, invalidLine)
          }
        }
        source.close()
        val (linesResult, invalidLinesResult) = (lines.result(), invalidLines.result())
        logLinesAndInvalidLines(linesResult, invalidLinesResult)
        Weights(linesResult, invalidLinesResult)
      }
  }
  
  implicit object PulsesTransformer extends Transformer[Pulses] {
    def transform(path: String, delimiter: String = ","): Try[Pulses] =
      Try {
        val lines = mutable.ArrayBuilder.make[Pulse]
        val invalidLines = mutable.ArrayBuilder.make[InvalidLine]
        val source = Source.fromFile(path, utf8)
        for (line <- source.getLines) {
          val columns = line.split(delimiter).map(_.trim)
          validate[Pulse](columns) match {
            case Success(pulse) => lines += pulse
            case Failure(invalidLine) => invalidLines += InvalidLine(line, invalidLine)
          }
        }
        source.close()
        val (linesResult, invalidLinesResult) = (lines.result(), invalidLines.result())
        logLinesAndInvalidLines(linesResult, invalidLinesResult)
        Pulses(linesResult, invalidLinesResult)
      }
  }

  implicit object PulseOxygensTransformer extends Transformer[PulseOxygens] {
    def transform(path: String, delimiter: String = ","): Try[PulseOxygens] =
      Try {
        val lines = mutable.ArrayBuilder.make[PulseOxygen]
        val invalidLines = mutable.ArrayBuilder.make[InvalidLine]
        val source = Source.fromFile(path, utf8)
        for (line <- source.getLines) {
          val columns = line.split(delimiter).map(_.trim)
          validate[PulseOxygen](columns) match {
            case Success(pulseOxygen) => lines += pulseOxygen
            case Failure(invalidLine) => invalidLines += InvalidLine(line, invalidLine)
          }
        }
        source.close()
        val (linesResult, invalidLinesResult) = (lines.result(), invalidLines.result())
        logLinesAndInvalidLines(linesResult, invalidLinesResult)
        PulseOxygens(linesResult, invalidLinesResult)
      }
  }
}