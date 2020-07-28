package objektwerks.chart

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.slf4j.LoggerFactory

import scala.util.Success
import scala.util.Failure

import Transformer._

class TransformerTest extends AnyFunSuite with Matchers {
  val logger = LoggerFactory.getLogger(this.getClass)
  
  test("types") {
    import scala.reflect._
    def isTypeof[T: ClassTag](list: List[T]): ClassTag[T] = classTag[T] match {
      case g if g === classTag[Glucose] => println(s"*** glucose: ${list.toString}"); g
      case m if m === classTag[Med] => println(s"*** med: ${list.toString}"); m
    }
    isTypeof(List.empty[Glucose]) === classTag[Glucose] shouldBe true
    isTypeof(List.empty[Med]) === classTag[Med] shouldBe true
  }

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
        logger.info(s"glucose lines [${lines.length}]: ${lines.toList.map(g => "\n" + g.toString)}")
        logger.info(s"glucose errors [${errors.length}]: ${errors.toList.map(g => "\n" + g.toString)}")
        lines.length shouldEqual linesCount
        errors.length shouldEqual errorsCount
      case Failure(error) =>
        logger.error(s"glucose test failure: ${error.printStackTrace()}")
        fail
    }
    ()
  }

  private def csvToMedsTest(path: String, linesCount: Int, errorsCount: Int): Unit = {
    csvToMeds(path) match {
      case Success((lines, errors)) =>
        logger.info(s"meds lines [${lines.length}]: ${lines.toList.map(m => "\n" + m.toString)}")
        logger.info(s"meds errors [${errors.length}]: ${errors.toList.map(m => "\n" + m.toString)}")
        lines.length shouldEqual linesCount
        errors.length shouldEqual errorsCount
      case Failure(error) =>
        logger.error(s"meds test failure: ${error.printStackTrace()}")
        fail
    }
    ()
  }
}