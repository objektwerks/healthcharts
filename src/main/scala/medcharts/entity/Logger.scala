package medcharts.entity

import org.slf4j.LoggerFactory

object Logger {
  private val logger = LoggerFactory.getLogger(getClass)
  
  def logEntitiesAndInvalidLines[E, IL](entities: Array[E], invalidLines: Array[IL]): Unit = {
    logger.info(s"entities [${entities.length}]: ${entities.toList.map(entity => "\n" + entity.toString)}")
    logger.info(s"invalid lines [${invalidLines.length}]: ${invalidLines.toList.map(line => "\n" + line.toString)}")
  }

  def logIOFailure(failure: Throwable, path: String): Unit = logger.error(s"IO failure at: $path -> ${failure.getMessage}")
}