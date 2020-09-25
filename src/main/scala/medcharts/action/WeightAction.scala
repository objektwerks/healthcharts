package medcharts.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction
import medcharts.Conf
import medcharts.chart.WeightChart
import medcharts.entity._
import medcharts.entity.Transformer._
import medcharts.panel.ChartPanelBuilder
import medcharts.ui.{Frame, PathDialog}

class WeightAction(name: String, frame: Frame) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelWeightCsv).view
    path match {
      case Some( weightCsvPath ) =>
        val weights = transformEntities[Weight](weightCsvPath)
        val chart = WeightChart.build(weights)
        val chartPanel = ChartPanelBuilder.build(chart, weights)
        frame.addCompositeChartPanel(s"${Conf.titleWeight}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}