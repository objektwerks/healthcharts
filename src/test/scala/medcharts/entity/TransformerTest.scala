package medcharts.entity

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

  test("med") {
    testTransformer[Med]("./data/med/med.txt", 18, 0)
    testTransformer[Med]("./data/med/med-invalid.txt", 16, 2)
  }

  test("pulse") {
    testTransformer[Pulse]("./data/pulse/pulse.txt", 7, 0)
    testTransformer[Pulse]("./data/pulse/pulse-invalid.txt", 5, 2)
  }
  
  test("pulse oxygen") {
    testTransformer[PulseOxygen]("./data/pulseoxygen/pulse-oxygen.txt", 7, 0)
    testTransformer[PulseOxygen]("./data/pulseoxygen/pulse-oxygen-invalid.txt", 5, 2)
  }
  
  test("respiration") {
    testTransformer[Respiration]("./data/respiration/respiration.txt", 7, 0)
    testTransformer[Respiration]("./data/respiration/respiration-invalid.txt", 5, 2)
  }
  
  test("temperature") {
    testTransformer[Temperature]("./data/temperature/temperature.txt", 7, 0)
    testTransformer[Temperature]("./data/temperature/temperature-invalid.txt", 5, 2)
  }

  test("vitals") {
    testTransformer[Vitals]("./data/vitals/vitals.txt", 12, 0)
    testTransformer[Vitals]("./data/vitals/vitals-invalid.txt", 10, 2)
  }

  test("weight") {
    testTransformer[Weight]("./data/weight/weight.txt", 7, 0)
    testTransformer[Weight]("./data/weight/weight-invalid.txt", 5, 2)
  }  

  private def testTransformer[E: ClassTag](path: String,
                                           entitiesCount: Int,
                                           invalidLinesCount: Int)(implicit validator: Validator[E]): Unit = {
    Transformer.transform[E](path) match {
      case Success(entities) =>
        entities.entities.length shouldEqual entitiesCount
        entities.invalidLines.length shouldEqual invalidLinesCount
      case Failure(failure) =>
        Logger.logIOFailure(failure, path)
        fail
    }
    ()
  }
}