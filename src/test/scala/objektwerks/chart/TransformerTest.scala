package objektwerks.chart

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.slf4j.LoggerFactory

import scala.util.Success
import scala.util.Failure

import Transformer._

class TransformerTest extends AnyFunSuite with Matchers {
  val logger = LoggerFactory.getLogger(this.getClass)

  test("glucose") {
    csvToGlucoseTest("./data/glucose/glucose.txt", 23, 0)
    csvToGlucoseTest("./data/glucose/glucose-invalid.txt", 20, 3)
 }

  test("meds") {
    csvToMedsTest("./data/meds/meds.txt", 23, 0)
    csvToMedsTest("./data/meds/meds-invalid.txt", 20, 3)
  }

  private def csvToGlucoseTest(path: String, linesCount: Int, errorsCount: Int): Unit = {
    csvToGlucose(path) match {
      case Success((lines, errors)) =>
        logLinesAndErrors( (lines, errors) )
        lines.length shouldEqual linesCount
        errors.length shouldEqual errorsCount
      case Failure(error) =>
        logger.error(s"glucose test failure: ${error.getMessage}")
        fail
    }
    ()
  }

  private def csvToMedsTest(path: String, linesCount: Int, errorsCount: Int): Unit = {
    csvToMeds(path) match {
      case Success((lines, errors)) =>
        logLinesAndErrors( (lines, errors) )
        lines.length shouldEqual linesCount
        errors.length shouldEqual errorsCount
      case Failure(error) =>
        logger.error(s"meds test failure: ${error.getMessage}")
        fail
    }
    ()
  }
}