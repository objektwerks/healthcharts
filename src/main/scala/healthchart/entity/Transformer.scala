package healthchart.entity

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try, Using}

import healthchart.Logger
import healthchart.Logger.*
import healthchart.entity.Validator.*

object Transformer:
  private val utf8 = Codec.UTF8.name

  def transform[E: ClassTag](path: String,
                             delimiter: String = ",")(implicit validator: Validator[E]): Try[Entities[E]] =
    Using( Source.fromFile(path, utf8) ) { source =>
      val entitiesBuilder = mutable.ArrayBuilder.make[E]
      val invalidEntitiesBuilder = mutable.ArrayBuilder.make[InvalidEntity]
      var number = 1
      for (line <- source.getLines()) {
        val columns = line.split(delimiter).map(_.trim)
        validate[E](number, columns) match
          case Success(entity) => entitiesBuilder += entity
          case Failure(error) => invalidEntitiesBuilder += InvalidEntity(number, line, error)
        number += 1
      }
      val (entities, invalidEntities) = (entitiesBuilder.result(), invalidEntitiesBuilder.result())
      logEntitiesAndInvalidEntities(entities, invalidEntities)
      Entities[E](entities, invalidEntities)
    }

  def transformEntities[E: ClassTag](path: String)(implicit validator: Validator[E]): Entities[E] =
    transform[E](path) match
      case Success(entities) => entities
      case Failure(failure) =>
        Logger.logFileIOFailure(path, failure)
        Entities.empty