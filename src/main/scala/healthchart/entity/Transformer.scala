package healthchart.entity

import healthchart.Logger
import healthchart.Logger._
import healthchart.entity.Validator._

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try}

object Transformer {
  private val utf8 = Codec.UTF8.name

  def transform[E: ClassTag](path: String,
                             delimiter: String = ",")(implicit validator: Validator[E]): Try[Entities[E]] =
    Try {
      val entitiesBuilder = mutable.ArrayBuilder.make[E]
      val invalidEntitiesBuilder = mutable.ArrayBuilder.make[InvalidEntity]
      val source = Source.fromFile(path, utf8)
      var number = 1
      for (line <- source.getLines()) {
        val columns = line.split(delimiter).map(_.trim)
        validate[E](number, columns) match {
          case Success(entity) => entitiesBuilder += entity
          case Failure(error) => invalidEntitiesBuilder += InvalidEntity(number, line, error)
        }
        number += 1
      }
      source.close()
      val (entities, invalidEntities) = (entitiesBuilder.result(), invalidEntitiesBuilder.result())
      logEntitiesAndInvalidEntities(entities, invalidEntities)
      Entities[E](entities, invalidEntities)
    }

  def transformEntities[E: ClassTag](path: String)(implicit validator: Validator[E]): Entities[E] =
    Transformer.transform[E](path) match {
      case Success(entities) => entities
      case Failure(failure) =>
        Logger.logFileIOFailure(path, failure)
        Entities.empty
    }
}