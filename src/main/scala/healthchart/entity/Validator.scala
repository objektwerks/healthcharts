package healthchart.entity

import scala.util.Try

import healthchart.entity.Converter.*

sealed trait Validator[E]:
  def validate(number: Int, columns: Array[String]): Try[E]

object Validator:
  def validate[E](number: Int, columns: Array[String])(using validator: Validator[E]): Try[E] = validator.validate(number, columns)

  given Validator[BloodPressure] with
    def validate(number: Int, columns: Array[String]): Try[BloodPressure] =
      Try:
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val systolic = columns(1).toInt
        val diastolic = columns(2).toInt
        validateBloodPressure(systolic, diastolic)
        BloodPressure(number, datetime, systolic, diastolic)

  given Validator[CaloriesWeight] with
    def validate(number: Int, columns: Array[String]): Try[CaloriesWeight] =
      Try:
        validateColumnCount(columns.length, 4)
        val datetime = datetimeToMinute(columns(0))
        val in = columns(1).toInt
        val out = columns(2).toInt
        val weight = columns(3).toDouble
        validateCaloriesWeight(in, out, weight)
        CaloriesWeight(number, datetime, in, out, weight)

  given Validator[Glucose] with
    def validate(number: Int, columns: Array[String]): Try[Glucose] =
      Try:
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val level = columns(1).toInt
        validateGlucose(level)
        Glucose(number, datetime, level)

  given Validator[Med] with
    def validate(number: Int, columns: Array[String]): Try[Med] =
      Try:
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val medTypeId = columns(1).toInt
        val medtype = MedType.idToMedType(medTypeId)
        val dosage = columns(2).toInt
        validateMed(dosage)
        Med(number, datetime, medtype, dosage)

  given Validator[GlucoseMed] with
    def validate(number: Int, columns: Array[String]): Try[GlucoseMed] =
      Try:
        validateColumnCount(columns.length, 4)
        val datetime = datetimeToMinute(columns(0))
        val level = columns(1).toInt
        val medTypeId = columns(2).toInt
        val medtype = MedType.idToMedType(medTypeId)
        val dosage = columns(3).toInt
        validateGlucoseMed(level, dosage)
        GlucoseMed(number, datetime, level, medtype, dosage)

  given Validator[Pulse] with
    def validate(number: Int, columns: Array[String]): Try[Pulse] =
      Try:
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val beatsPerMinute = columns(1).toInt
        validatePulse(beatsPerMinute)
        Pulse(number, datetime, beatsPerMinute)

  given Validator[PulseOxygen] with
    def validate(number: Int, columns: Array[String]): Try[PulseOxygen] =
      Try:
        validateColumnCount(columns.length, 3)
        val datetime = datetimeToMinute(columns(0))
        val beatsPerMinute = columns(1).toInt
        val bloodOxygenPercentage = columns(2).toInt
        validatePulseOxygen(beatsPerMinute, bloodOxygenPercentage)
        PulseOxygen(number, datetime, beatsPerMinute, bloodOxygenPercentage)

  given Validator[Respiration] with
    def validate(number: Int, columns: Array[String]): Try[Respiration] =
      Try:
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val breathesPerMinute = columns(1).toInt
        validateRespiration(breathesPerMinute)
        Respiration(number, datetime, breathesPerMinute)

  given Validator[Temperature] with
    def validate(number: Int, columns: Array[String]): Try[Temperature] =
      Try:
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val degrees = columns(1).toDouble
        validateTemperature(degrees)
        Temperature(number, datetime, degrees)

  given Validator[Vitals] with
    def validate(number: Int, columns: Array[String]): Try[Vitals] =
      Try:
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

  given Validator[Weight] with
    def validate(number: Int, columns: Array[String]): Try[Weight] =
      Try:
        validateColumnCount(columns.length, 2)
        val datetime = datetimeToMinute(columns(0))
        val pounds = columns(1).toDouble
        validateWeight(pounds)
        Weight(number, datetime, pounds)

  private def validateCaloriesWeight(in: Int, out: Int, weight: Double): Unit =
    require(in >= 0 && in <= 9999, s"$in calories in not >= 0 and <= 9999")
    require(out >= 0 && out <= 9999, s"$out calories out not >= 0 and <= 9999")
    validateWeight(weight)

  private def validateColumnCount(length: Int, count: Int): Unit =
    require(length == count, s"$length column count != $count")

  private def validateBloodPressure(systolic: Int, diastolic: Int): Unit =
    require(systolic >= 120 && systolic <= 200, s"$systolic systolic not >= 120 and <= 200")
    require(diastolic >= 80 && diastolic <= 120, s"$diastolic diastolic not >= 80 and <= 120")

  private def validateGlucose(level: Int): Unit =
    require(level >= 0 && level <= 300, s"$level level not >= 0 and <= 300")

  private def validateMed(dosage: Int): Unit =
    require(dosage >= 1 && dosage <= 100, s"$dosage dosage not >= 1 and <= 100")

  private def validateGlucoseMed(level: Int, dosage:Int): Unit =
    validateGlucose(level)
    validateMed(dosage)

  private def validatePulse(beatsPerMinute: Int): Unit =
    require(beatsPerMinute >= 40 && beatsPerMinute <= 200, s"$beatsPerMinute beats per minute not >= 40 and <= 200")

  private def validatePulseOxygen(beatsPerMinute: Int, bloodOxygenPercentage: Int): Unit = {
    validatePulse(beatsPerMinute)
    require(bloodOxygenPercentage >= 50 && bloodOxygenPercentage <= 100, s"$bloodOxygenPercentage blood oxygen percentage not >= 50 and <= 100")
  }

  private def validateRespiration(breathesPerMinute: Int): Unit =
    require(breathesPerMinute >= 12 && breathesPerMinute <= 25, s"$breathesPerMinute breathes per minute not >= 12 and <= 25")

  private def validateTemperature(degrees: Double): Unit =
    require(degrees >= 95.00 && degrees <= 105.00, s"$degrees degrees not >= 95.00 and <= 105.00")

  private def validateWeight(pounds: Double): Unit =
    require(pounds > 0.00 && pounds <= 500.00, s"$pounds pounds not > 0.00 and <= 500.00")