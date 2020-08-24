package objektwerks.chart

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Success
import scala.util.Failure

import Logger._
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
      case Success(glucoses) => 
        glucoses.lines.length shouldEqual linesCount
        glucoses.invalidLines.length shouldEqual invalidLinesCount
      case Failure(failure) =>
        logIOFailure(failure, path)
        fail
    }
    ()
  }

  private def csvToMedsTest(path: String, linesCount: Int, invalidLinesCount: Int): Unit = {
    csvToMeds(path) match {
      case Success(meds) => 
        meds.lines.length shouldEqual linesCount
        meds.invalidLines.length shouldEqual invalidLinesCount
      case Failure(failure) =>
        logIOFailure(failure, path)
        fail
    }
    ()
  }
}