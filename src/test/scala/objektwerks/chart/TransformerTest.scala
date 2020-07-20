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
    csvToGlucose("./data/glucose/glucose.txt") match {
      case Success((lines, errors)) =>
        lines.length shouldEqual 23
        errors.isEmpty shouldBe true
      case Failure(error) =>
        logger.error(s"glucose test fail: ${error.printStackTrace()}")
        fail
    }
  }

  test("glucose invalid") {
    csvToGlucose("./data/glucose/glucose-invalid.txt") match {
      case Success((lines, errors)) =>
        lines.length shouldEqual 20
        errors.length shouldEqual 3
      case Failure(error) =>
        logger.error(s"glucose test fail: ${error.printStackTrace()}")
        fail
    }
  }

  test("meds") {
    csvToMeds("./data/meds/meds.txt") match {
      case Success((lines, errors)) =>
        lines.length shouldEqual 23
        errors.isEmpty shouldBe true
      case Failure(error) => 
        logger.error(s"meds test fail: ${error.printStackTrace()}")
        fail
    }
  }

  test("meds-invalid") {
    csvToMeds("./data/meds/meds-invalid.txt") match {
      case Success((lines, errors)) =>
        lines.length shouldEqual 20
        errors.length shouldEqual 3
      case Failure(error) => 
        logger.error(s"meds test fail: ${error.printStackTrace()}")
        fail
    }
  }
}