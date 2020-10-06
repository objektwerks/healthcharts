package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.WeightChart
import healthchart.entity._
import healthchart.entity.Transformer._
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

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