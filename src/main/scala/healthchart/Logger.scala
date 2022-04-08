package healthchart

import org.slf4j.LoggerFactory

object Logger:
  private val logger = LoggerFactory.getLogger(getClass)
  
  def logEntitiesAndInvalidEntities[E, IE](entities: Array[E], invalidEntities: Array[IE]): Unit =
    logger.info(s"*** Entities [${entities.length}]: ${entities.toList.map(entity => "\n" + entity.toString)}")
    logger.info(s"*** Invalid Entities [${invalidEntities.length}]: ${invalidEntities.toList.map(line => "\n" + line.toString)}")

  def logFileIOFailure(path: String, failure: Throwable): Unit =
    logger.error(s"*** File IO failure at: $path -> ${failure.getMessage}")