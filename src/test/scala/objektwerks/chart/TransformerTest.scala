package objektwerks.chart

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Success
import scala.util.Failure

import Transformer._

class TransformerTest extends AnyFunSuite with Matchers {
  test("glucose") {
    csvToGlucoseTest("./data/glucose/glucose.txt", 23, 0)
    csvToGlucoseTest("./data/glucose/glucose-invalid.txt", 20, 3)
 }

  test("meds") {
    csvToMedsTest("./data/meds/meds.txt", 23, 0)
    csvToMedsTest("./data/meds/meds-invalid.txt", 20, 3)
  }

  private def csvToGlucoseTest(path: String, linesCount: Int, invalidLinesCount: Int): Unit = {
    csvToGlucose(path) match {
      case Success((lines, errors)) => 
        lines.length shouldEqual linesCount
        errors.length shouldEqual invalidLinesCount
      case Failure(failure) =>
        logIOFailure(failure, path)
        fail
    }
    ()
  }

  private def csvToMedsTest(path: String, linesCount: Int, invalidLinesCount: Int): Unit = {
    csvToMeds(path) match {
      case Success((lines, errors)) => 
        lines.length shouldEqual linesCount
        errors.length shouldEqual invalidLinesCount
      case Failure(failure) =>
        logIOFailure(failure, path)
        fail
    }
    ()
  }
}