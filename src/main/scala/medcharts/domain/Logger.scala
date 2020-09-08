package medcharts.domain

import org.slf4j.LoggerFactory

object Logger {
  private val logger = LoggerFactory.getLogger(getClass)
  
  def logLinesAndInvalidLines[L, IL](lines: Array[L], invalidLines: Array[IL]): Unit = {
    logger.info(s"lines [${lines.length}]: ${lines.toList.map(g => "\n" + g.toString)}")
    logger.info(s"errors [${invalidLines.length}]: ${invalidLines.toList.map(g => "\n" + g.toString)}")
  }

  def logIOFailure(failure: Throwable, path: String): Unit = logger.error(s"Failed to load $path: ${failure.getMessage}")
}