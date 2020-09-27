package medcharts.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import medcharts.Conf
import medcharts.chart.GlucoseMedChart
import medcharts.entity._
import medcharts.entity.Transformer._
import medcharts.panel.ChartPanelBuilder
import medcharts.ui.{Frame, PathDialog}

class GlucoseMedAction(name: String, frame: Frame) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelGlucoseMedCsv).view
    path match {
      case Some( glucosemedCsvPath ) =>
        val glucosemeds = transformEntities[GlucoseMed](glucosemedCsvPath)
        val chart = GlucoseMedChart.build(glucosemeds)
        val chartPanel = ChartPanelBuilder.build(chart, glucosemeds)
        frame.addCompositeChartPanel(s"${Conf.titleGlucoseMed}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}