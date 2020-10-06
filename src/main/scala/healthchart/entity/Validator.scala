package healthchart.entity

import healthchart.entity.Converter._

import scala.util.Try

trait Validator[E] {
  def validate(number: Int, columns: Array[String]): Try[E]
}

object Validator {
  def validate[E](number: Int, columns: Array[String])(implicit validator: Validator[E]): Try[E] = validator.validate(number, columns)

  private def validateColumnCount(length: Int, count: Int): Unit =
    require(length == count, s"column count != $count")

  private def validateBloodPressure(systolic: Int, diastolic: Int): Unit = {
    require(systolic >= 120 && systolic <= 200, s"systolic not >= 120 and <= 200")
    require(diastolic >= 80 && diastolic <= 120, s"diastolic not >= 80 and <= 120")
  }

  private def validateGlucose(level: Int): Unit =
    require(level >= 0 && level <= 300, s"level not >= 0 and <= 300")

  private def validateMed(dosage: Int): Unit =
    require(dosage >= 1 && dosage <= 100, "dosage not >= 1 and <= 100")

  private def validateGlucoseMed(level: Int, dosage:Int): Unit = {
    validateGlucose(level)
    validateMed(dosage)
  }

  private def validatePulse(beatsPerMinute: Int): Unit =
    require(beatsPerMinute >= 40 && beatsPerMinute <= 200, s"beats per minute not >= 40 and <= 200")

  private def validatePulseOxygen(beatsPerMinute: Int, bloodOxygenPercentage: Int): Unit = {
    validatePulse(beatsPerMinute)
    require(bloodOxygenPercentage >= 50 && bloodOxygenPercentage <= 100, s"blood oxygen percentage not >= 50 and <= 100")
  }

  private def validateRespiration(breathesPerMinute: Int): Unit =
    require(breathesPerMinute >= 12 && breathesPerMinute <= 25, s"breathes per minute not >= 12 and <= 25")

  private def validateTemperature(degrees: Double): Unit =
    require(degrees >= 95.0 && degrees <= 105.0, s"temperature, in degrees, not >= 95.0 and <= 105.0")

  private def validateWeight(pounds: Double): Unit =
    require(pounds > 0.00 && pounds <= 500.00, s"pounds not > 0.00 and <= 500.00")

  implicit object BloodPressureValidator extends Validator[BloodPressure] {
    def validate(number: Int, columns: Array[String]): Try[BloodPressure] =
      Try {
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val systolic = columns(1).toInt
        val diastolic = columns(2).toInt
        validateBloodPressure(systolic, diastolic)
        BloodPressure(number, datetime, systolic, diastolic)
      }
  }

  implicit object GlucoseValidator extends Validator[Glucose] {
    def validate(number: Int, columns: Array[String]): Try[Glucose] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val level = columns(1).toInt
        validateGlucose(level)
        Glucose(number, datetime, level)
      }
  }

  implicit object MedValidator extends Validator[Med] {
    def validate(number: Int, columns: Array[String]): Try[Med] =
      Try {
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val medTypeId = columns(1).toInt
        val medtype = MedType.idToMedType(medTypeId)
        val dosage = columns(2).toInt
        validateMed(dosage)
        Med(number, datetime, medtype, dosage)
      }
  }

  implicit object GlucoseMedValidator extends Validator[GlucoseMed] {
    def validate(number: Int, columns: Array[String]): Try[GlucoseMed] =
      Try {
        validateColumnCount(columns.length, 4)
        val datetime = datetimeToMinute(columns(0))
        val level = columns(1).toInt
        val medTypeId = columns(2).toInt
        val medtype = MedType.idToMedType(medTypeId)
        val dosage = columns(3).toInt
        validateGlucoseMed(level, dosage)
        GlucoseMed(number, datetime, level, medtype, dosage)
      }
  }

  implicit object PulseValidator extends Validator[Pulse] {
    def validate(number: Int, columns: Array[String]): Try[Pulse] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val beatsPerMinute = columns(1).toInt
        validatePulse(beatsPerMinute)
        Pulse(number, datetime, beatsPerMinute)
      }
  }

  implicit object PulseOxygenValidator extends Validator[PulseOxygen] {
    def validate(number: Int, columns: Array[String]): Try[PulseOxygen] =
      Try {
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val beatsPerMinute = columns(1).toInt
        val bloodOxygenPercentage = columns(2).toInt
        validatePulseOxygen(beatsPerMinute, bloodOxygenPercentage)
        PulseOxygen(number, datetime, beatsPerMinute, bloodOxygenPercentage)
      }
  }

  implicit object RespirationValidator extends Validator[Respiration] {
    def validate(number: Int, columns: Array[String]): Try[Respiration] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val breathesPerMinute = columns(1).toInt
        validateRespiration(breathesPerMinute)
        Respiration(number, datetime, breathesPerMinute)
      }
  }

  implicit object TemperatureValidator extends Validator[Temperature] {
    def validate(number: Int, columns: Array[String]): Try[Temperature] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val degrees = columns(1).toDouble
        validateTemperature(degrees)
        Temperature(number, datetime, degrees)
      }
  }

  implicit object VitalsValidator extends Validator[Vitals] {
    def validate(number: Int, columns: Array[String]): Try[Vitals] =
      Try {
        validateColumnCount(columns.length, 7)
        val datetime = datetimeToMinute(columns(0))
        val temperature = columns(1).toDouble
        val respiration = columns(2).toInt
        val pulse = columns(3).toInt
        val oxygen = columns(4).toInt
        val systolic = columns(5).toInt
        val diastolic = columns(6).toInt
        validateTemperature(temperature)
        validateRespiration(respiration)
        validatePulse(pulse)
        validatePulseOxygen(pulse, oxygen)
        validateBloodPressure(systolic, diastolic)
        Vitals(number, datetime, temperature, respiration, pulse, oxygen, systolic, diastolic)
      }
  }

  implicit object WeightValidator extends Validator[Weight] {
    def validate(number: Int, columns: Array[String]): Try[Weight] =
      Try {
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val pounds = columns(1).toDouble
        validateWeight(pounds)
        Weight(number, datetime, pounds)
      }
  }  
}