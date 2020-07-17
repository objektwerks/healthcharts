package objektwerks.chart

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class TransformerTest extends AnyFunSuite with Matchers {
  import Transformer._

  test("glucose") {
    csvToGlucose("./data/glucose/glucose.txt").get.length shouldEqual 23
  }

  test("meds") {
    csvToMeds("./data/meds/meds.txt").get.length shouldEqual 23
  }
}