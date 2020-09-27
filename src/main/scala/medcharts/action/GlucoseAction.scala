package medcharts.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import medcharts.Conf
import medcharts.chart.GlucoseChart
import medcharts.entity._
import medcharts.entity.Transformer._
import medcharts.panel.ChartPanelBuilder
import medcharts.ui.{Frame, PathDialog}

class GlucoseAction(name: String, frame: Frame) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelGlucoseCsv).view
    path match {
      case Some( glucoseCsvPath ) =>
        val glucoses = transformEntities[Glucose](glucoseCsvPath)
        val chart = GlucoseChart.build(glucoses)
        val chartPanel = ChartPanelBuilder.build(chart, glucoses)
        frame.addCompositeChartPanel(s"${Conf.titleGlucose}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}