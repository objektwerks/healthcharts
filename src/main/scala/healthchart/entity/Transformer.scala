package healthchart.entity

import com.typesafe.scalalogging.Logger

import ox.supervised

import scala.collection.mutable
import scala.io.{Codec, Source}
import scala.reflect.ClassTag
import scala.util.{Failure, Success, Try, Using}

import healthchart.Logger.*
import healthchart.entity.Validator.*

object Transformer:
  private val utf8 = Codec.UTF8.name
  private val logger = Logger.apply(this.getClass())


  def transform[E: ClassTag](path: String,
                             delimiter: String = ",")(using validator: Validator[E]): Try[Entities[E]] =
    logger.info("*** Transformer.transform path: $path, delimiter: $delimter")
    Using( Source.fromFile(path, utf8) ) { source =>
      supervised:
        val entitiesBuilder = mutable.ArrayBuilder.make[E]
        val invalidEntitiesBuilder = mutable.ArrayBuilder.make[InvalidEntity]
        var number = 1
        for (line <- source.getLines())
          val columns = line.split(delimiter).map(_.trim)
          validate[E](number, columns) match
            case Success(entity) => entitiesBuilder += entity
            case Failure(error) => invalidEntitiesBuilder += InvalidEntity(number, line, error)
          number += 1
        val (entities, invalidEntities) = (entitiesBuilder.result(), invalidEntitiesBuilder.result())
        logEntitiesAndInvalidEntities(entities, invalidEntities)
        Entities[E](entities, invalidEntities)
    }

  def transformEntities[E: ClassTag](path: String)(using validator: Validator[E]): Entities[E] =
    transform[E](path) match
      case Success(entities) => entities
      case Failure(failure) =>
        logFileIOFailure(path, failure)
        Entities.empty