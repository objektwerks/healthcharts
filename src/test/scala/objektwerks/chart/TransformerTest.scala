package objektwerks.chart

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.util.Success
import scala.util.Failure

import Logger._
import Transformer._

class TransformerTest extends AnyFunSuite with Matchers {
  test("glucose") {
    testGlucosesTransformer("./data/glucose/glucose.txt", 12, 0)
    testGlucosesTransformer("./data/glucose/glucose-invalid.txt", 20, 3)
 }

  test("meds") {
    testMedsTransformer("./data/meds/meds.txt", 12, 0)
    testMedsTransformer("./data/meds/meds-invalid.txt", 20, 3)
  }

  private def testGlucosesTransformer(path: String, linesCount: Int, invalidLinesCount: Int): Unit = {
    transform[Glucoses](path) match {
      case Success(glucoses) => 
        glucoses.lines.length shouldEqual linesCount
        glucoses.invalidLines.length shouldEqual invalidLinesCount
      case Failure(failure) =>
        logIOFailure(failure, path)
        fail
    }
    ()
  }

  private def testMedsTransformer(path: String, linesCount: Int, invalidLinesCount: Int): Unit = {
    transform[Meds](path) match {
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