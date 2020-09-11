package medcharts.test

import medcharts.domain.{BloodPressure, Glucose, Logger, Med, Transformer, Validator}

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scala.reflect.ClassTag
import scala.util.{Failure, Success}

class TransformerTest extends AnyFunSuite with Matchers {
  test("blood pressure") {
    testTransformer[BloodPressure]("./data/bloodpressure/blood-pressure.txt", 7, 0)
    testTransformer[BloodPressure]("./data/bloodpressure/blood-pressure-invalid.txt", 5, 2)
  }

  test("glucose") {
    testTransformer[Glucose]("./data/glucose/glucose.txt", 18, 0)
    testTransformer[Glucose]("./data/glucose/glucose-invalid.txt", 16, 2)
  }

  test("meds") {
    testTransformer[Med]("./data/meds/meds.txt", 18, 0)
    testTransformer[Med]("./data/meds/meds-invalid.txt", 16, 2)
  }

  private def testTransformer[E: ClassTag](path: String,
                                           linesCount: Int,
                                           invalidLinesCount: Int)(implicit validator: Validator[E]): Unit = {
    Transformer.transform[E](path) match {
      case Success(entities) =>
        entities.lines.length shouldEqual linesCount
        entities.invalidLines.length shouldEqual invalidLinesCount
      case Failure(failure) =>
        Logger.logIOFailure(failure, path)
        fail
    }
    ()
  }
}