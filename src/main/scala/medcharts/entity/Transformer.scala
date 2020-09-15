package medcharts.entity

import medcharts.entity.Logger._
import medcharts.entity.Validator._

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
      val invalidLinesBuilder = mutable.ArrayBuilder.make[InvalidLine]
      val source = Source.fromFile(path, utf8)
      for (line <- source.getLines) {
        val columns = line.split(delimiter).map(_.trim)
        validate[E](columns) match {
          case Success(entity) => entitiesBuilder += entity
          case Failure(error) => invalidLinesBuilder += InvalidLine(line, error)
        }
      }
      source.close()
      val (entities, invalidLines) = (entitiesBuilder.result(), invalidLinesBuilder.result())
      logEntitiesAndInvalidLines(entities, invalidLines)
      new Entities[E](entities, invalidLines)
    }
}