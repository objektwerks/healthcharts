package healthchart.entity

import org.jfree.data.time.Minute

import scala.collection.immutable.SortedMap
import scala.reflect.ClassTag

sealed trait Entity:
  def line: Int
  def datetime: Minute

final case class Entities[E](entities: Array[E],
                             invalidEntities: Array[InvalidEntity]):
  def toEntity: Array[Entity] = entities.asInstanceOf[Array[Entity]]

object Entities:
  def empty[E: ClassTag]: Entities[E] = Entities[E]( Array.empty[E], Array.empty[InvalidEntity] )

final case class InvalidEntity(number: Int,
                               line: String,
                               error: Throwable):
  /** Error getMessage starts with: 'requirement failed: ' The substring(20) cuts it out. */
  override def toString: String = s"$number, $line, [ ${error.getMessage.substring(20)} ]"

final case class BloodPressure(line: Int,
                               datetime: Minute, 
                               systolic: Int, 
                               diastolic: Int) extends Entity derives CanEqual:
  override def toString: String = s"$line, $datetime, $systolic, $diastolic"

final case class CaloriesWeight(line: Int, 
                                datetime: Minute, 
                                in: Int, 
                                out: Int, 
                                weight: Double) extends Entity derives CanEqual:
  override def toString: String = s"$line, $datetime, $in, $out, $weight"

final case class Glucose(line: Int, 
                         datetime: Minute, 
                         level: Int) extends Entity derives CanEqual:
  override def toString: String = s"$line, $datetime, $level"

object MedType extends Enumeration:
  val insulin = Value(1, "insulin")
  val steroid = Value(2, "steroid")
  val idToMedType = SortedMap[Int, Value](insulin.id -> insulin, steroid.id -> steroid)

final case class Med(line: Int, 
                     datetime: Minute, 
                     medtype: MedType.Value,
                     dosage: Int) extends Entity derives CanEqual:
  override def toString: String = s"$line, $datetime, $medtype, $dosage"

final case class GlucoseMed(line: Int, 
                            datetime: Minute, 
                            level: Int, 
                            medtype: MedType.Value,
                            dosage: Int) extends Entity derives CanEqual:
  override def toString: String = s"$line, $datetime, $level, $medtype, $dosage"

final case class Pulse(line: Int, 
                       datetime: Minute, 
                       beatsPerMinute: Int) extends Entity derives CanEqual:
  override def toString: String = s"$line, $datetime, $beatsPerMinute"

final case class PulseOxygen(line: Int, 
                             datetime: Minute, 
                             beatsPerMinute: Int, 
                             bloodOxygenPercentage: Int) extends Entity:
  override def toString: String = s"$line, $datetime, $beatsPerMinute, $bloodOxygenPercentage"

final case class Respiration(line: Int, 
                             datetime: Minute, 
                             breathesPerMinute: Int) extends Entity:
  override def toString: String = s"$line, $datetime, $breathesPerMinute"

final case class Temperature(line: Int, 
                             datetime: Minute, 
                             degrees: Double) extends Entity:
  override def toString: String = s"$line, $datetime, $degrees"

final case class Vitals(line: Int,
                        datetime: Minute,
                        temperature: Double,
                        respiration: Int,
                        pulse: Int,
                        oxygen: Int,
                        systolic: Int,
                        diastolic: Int) extends Entity:
  override def toString: String = s"$line, $datetime, $temperature, $respiration, $pulse, $oxygen, $systolic, $diastolic"

final case class Weight(line: Int, 
                        datetime: Minute, 
                        pounds: Double) extends Entity:
  override def toString: String = s"$line, $datetime, $pounds"