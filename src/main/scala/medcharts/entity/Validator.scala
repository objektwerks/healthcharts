package medcharts.entity

import medcharts.entity.Converter._

import scala.util.Try

trait Validator[E] {
  def validate(columns: Array[String]): Try[E]
}

object Validator {
  def validate[E](columns: Array[String])(implicit validator: Validator[E]): Try[E] = validator.validate(columns)

  private def validateColumnCount(length: Int, count: Int): Unit = require(length == count, s"column count != $count")

  implicit object BloodPressureValidator extends Validator[BloodPressure] {
    def validate(columns: Array[String]): Try[BloodPressure] =
      Try {
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val systolic = columns(1).toInt
        val diastolic = columns(2).toInt
        require(systolic >= 120 && systolic <= 200, s"systolic not >= 120 and <= 200")
        require(diastolic >= 80 && diastolic <= 120, s"diastolic not >= 80 and <= 120")
        BloodPressure(datetime, systolic, diastolic)
      }
  }

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
        require(breathesPerMinute >= 12 && breathesPerMinute <= 25, s"breathes per minute not >= 12 and <= 25")
        Respiration(datetime, breathesPerMinute)
      }
  }

  implicit object TemperatureValidator extends Validator[Temperature] {
    def validate(columns: Array[String]): Try[Temperature] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val degrees = columns(1).toDouble
        require(degrees >= 95.0 && degrees <= 105.0, s"temperature, in degrees, not >= 95.0 and <= 105.0")
        Temperature(datetime, degrees)
      }
  }

  implicit object VitalsValidator extends Validator[Vitals] {
    def validate(columns: Array[String]): Try[Vitals] =
      Try {
        validateColumnCount(columns.length, 7)
        val datetime = datetimeToMinute(columns(0))

        val temperature = columns(1).toDouble
        require(temperature >= 95.0 && temperature <= 105.0, s"temperature, in degrees, not >= 95.0 and <= 105.0")

        val respiration = columns(2).toInt
        require(respiration >= 12 && respiration <= 25, s"breathes per minute not >= 12 and <= 25")

        val pulse = columns(3).toInt
        require(pulse >= 40 && pulse <= 200, s"beats per minute not >= 40 and <= 200")

        val oxygen = columns(4).toInt
        require(oxygen >= 50 && oxygen <= 100, s"blood oxygen percentage not >= 50 and <= 100")

        val systolic = columns(5).toInt
        require(systolic >= 120 && systolic <= 200, s"systolic not >= 120 and <= 200")

        val diastolic = columns(6).toInt
        require(diastolic >= 80 && diastolic <= 120, s"diastolic not >= 80 and <= 120")

        Vitals(datetime, temperature, respiration, pulse, oxygen, systolic, diastolic)
      }
  }

  implicit object WeightValidator extends Validator[Weight] {
    def validate(columns: Array[String]): Try[Weight] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val pounds = columns(1).toDouble
        require(pounds > 0.00 && pounds <= 500.00, s"pounds not > 0.00 and <= 500.00")
        Weight(datetime, pounds)
      }
  }  
}