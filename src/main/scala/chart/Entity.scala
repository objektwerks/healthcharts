package chart

import java.time.LocalDateTime

final case class Glucose(datetime: LocalDateTime, level: Int)

sealed trait MedType
final case object Insulin extends MedType
final case object Steroid extends MedType

final case class Med(datetime: LocalDateTime, typeof: MedType, dosage: Int)