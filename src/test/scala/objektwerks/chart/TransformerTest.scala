package objektwerks.chart

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import scala.util.Success
import scala.util.Failure

class TransformerTest extends AnyFunSuite with Matchers {
  import Transformer._

  test("glucose") {
    csvToGlucose("./data/glucose/glucose.txt") match {
      case Success((lines, errors)) =>
        lines.length shouldEqual 23
        errors.isEmpty shouldBe true
      case Failure(_) => fail
    }
  }

  test("meds") {
    csvToMeds("./data/meds/meds.txt") match {
      case Success((lines, errors)) =>
        lines.length shouldEqual 23
        errors.isEmpty shouldBe true
      case Failure(_) => fail
    }
  }
}