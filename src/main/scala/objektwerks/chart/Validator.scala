package objektwerks.chart

import scala.util.Try

import Converter._

trait Validator[E] {
  def validate(columns: Array[String]): Try[E]
}

object Validator {
  def validate[E](columns: Array[String])(implicit validator: Validator[E]): Try[E] = validator.validate(columns)

  implicit object GlucoseValidator extends Validator[Glucose] {
    val columnCount = 2

    def validate(columns: Array[String]): Try[Glucose] =
      Try {
        require(columns.length == columnCount, s"column count != $columnCount")
        val datetime = datetimeToMinute(columns(0))
        val level = columns(1).toInt
        require(level >= 0 && level <= 300, s"level not >= 0 and <= 300")
        Glucose(datetime, level)
      }
  }

  implicit object MedValidator extends Validator[Med] {
    import MedType._

    val columnCount = 3

    def validate(columns: Array[String]): Try[Med] =
      Try {
        require(columns.length == columnCount, s"column count != $columnCount")
        val datetime = datetimeToMinute(columns(0))
        val medtype = idToMedType(columns(1).toInt)
        val dosage = columns(2).toInt
        require(dosage >= 1 && dosage <= 100, "dosage not >= 1 and <= 100")
        Med(datetime, medtype, dosage)
      }
  }
}