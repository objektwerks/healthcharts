package medcharts.action

import java.awt.Color
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.{AbstractAction, BorderFactory}

import medcharts.entity._

import org.jfree.chart.{ChartPanel, JFreeChart}

import scala.reflect.ClassTag
import scala.util.{Failure, Success}

abstract class ChartAction(name: String) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  protected def transformEntities[E: ClassTag](path: String)(implicit validator: Validator[E]): Entities[E] =
    Transformer.transform[E](path) match {
      case Success(entities) => entities
      case Failure(failure) =>
        Logger.logIOFailure(failure, path)
        Entities.empty
    }

  protected def buildChartPanel(chart: JFreeChart): ChartPanel = {
    chart.getPlot.setBackgroundPaint(Color.LIGHT_GRAY)
    val chartPanel = new ChartPanel(chart)
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15))
    chartPanel.setInitialDelay(100)
    chartPanel.setReshowDelay(100)
    chartPanel.setDismissDelay(10000)
    chartPanel
  }
}