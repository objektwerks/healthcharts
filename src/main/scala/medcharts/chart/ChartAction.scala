package medcharts.chart

import java.util.concurrent.atomic.AtomicInteger

import javax.swing.{AbstractAction, BorderFactory, JOptionPane}

import medcharts.Conf
import medcharts.entity._
import medcharts.ui.Frame

import scala.reflect.ClassTag
import scala.util.{Failure, Success}
import org.jfree.chart.JFreeChart
import org.jfree.chart.ChartPanel

abstract class ChartAction(name: String, frame: Frame) extends AbstractAction(name) {
  protected  val counter = new AtomicInteger(1)

  protected def transformEntities[E: ClassTag](path: String)(implicit validator: Validator[E]): Entities[E] =
    Transformer.transform[E](path) match {
      case Success(entities) => entities
      case Failure(failure) =>
        Logger.logIOFailure(failure, path)
        Entities.empty
    }

  protected def buildChartPanel(chart: JFreeChart): ChartPanel = {
    val chartPanel = new ChartPanel(chart)
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15))
    chartPanel.setInitialDelay(100)
    chartPanel.setReshowDelay(100)
    chartPanel.setDismissDelay(10000)
    chartPanel
  }

  protected def showEntitiesErrorDialog(message: String): Unit =
    JOptionPane.showMessageDialog(frame, message, Conf.titleCsvFilesEmpty, JOptionPane.ERROR_MESSAGE)
}