package medcharts.domain

import medcharts.domain.Converter._

import scala.util.Try

trait Validator[E] {
  def validate(columns: Array[String]): Try[E]
}

object Validator {
  def validate[E](columns: Array[String])(implicit validator: Validator[E]): Try[E] = validator.validate(columns)

  private def validateColumnCount(length: Int, count: Int): Unit = require(length == count, s"column count != $count")

  implicit object GlucoseValidator extends Validator[Glucose] {
    def validate(columns: Array[String]): Try[Glucose] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val level = columns(1).toInt
        require(level >= 0 && level <= 300, s"level not >= 0 and <= 300")
        Glucose(datetime, level)
      }
  }

  implicit object MedValidator extends Validator[Med] {
    def validate(columns: Array[String]): Try[Med] =
      Try {
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val medTypeId = columns(1).toInt
        val medtype = MedType.idToMedType(medTypeId)
        val dosage = columns(2).toInt
        require(dosage >= 1 && dosage <= 100, "dosage not >= 1 and <= 100")
        Med(datetime, medtype, dosage)
      }
  }

  implicit object WeightValidator extends Validator[Weight] {
    def validate(columns: Array[String]): Try[Weight] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val pounds = columns(1).toDouble
        require(pounds > 0 && pounds < 500, s"pounds not > 0 and < 500")
        Weight(datetime, pounds)
      }
  }

  implicit object PulseValidator extends Validator[Pulse] {
    def validate(columns: Array[String]): Try[Pulse] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val beatsPerMinute = columns(1).toInt
        require(beatsPerMinute >= 40 && beatsPerMinute <= 200, s"beats per minute not >= 40 and <= 200")
        Pulse(datetime, beatsPerMinute)
      }
  }

  implicit object PulseOxygenValidator extends Validator[PulseOxygen] {
    def validate(columns: Array[String]): Try[PulseOxygen] =
      Try {
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val beatsPerMinute = columns(1).toInt
        val bloodOxygenPercentage = columns(2).toInt
        require(beatsPerMinute >= 40 && beatsPerMinute <= 200, s"beats per minute not >= 40 and <= 200")
        require(bloodOxygenPercentage >= 50 && bloodOxygenPercentage <= 100, s"blood oxygen percentage not >= 50 and <= 100")
        PulseOxygen(datetime, beatsPerMinute, bloodOxygenPercentage)
      }
  }

  implicit object RespirationValidator extends Validator[Respiration] {
    def validate(columns: Array[String]): Try[Respiration] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val breathesPerMinute = columns(1).toInt
        require(breathesPerMinute >= 40 && breathesPerMinute <= 200, s"breathes per minute not >= 40 and <= 200")
        Respiration(datetime, breathesPerMinute)
      }
  }
}