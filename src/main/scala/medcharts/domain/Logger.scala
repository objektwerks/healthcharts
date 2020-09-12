package medcharts.domain

import org.slf4j.LoggerFactory

object Logger {
  private val logger = LoggerFactory.getLogger(getClass)
  
  def logEntitiesAndInvalidLines[E, IL](entities: Array[E], invalidLines: Array[IL]): Unit = {
    logger.info(s"entities [${entities.length}]: ${entities.toList.map(g => "\n" + g.toString)}")
    logger.info(s"invalid lines [${invalidLines.length}]: ${invalidLines.toList.map(g => "\n" + g.toString)}")
  }

  def logIOFailure(failure: Throwable, path: String): Unit = logger.error(s"IO failure at: $path -> ${failure.getMessage}")
}