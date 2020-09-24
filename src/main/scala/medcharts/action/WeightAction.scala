package medcharts.action

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.WeightChart
import medcharts.entity._
import medcharts.ui.{Frame, PathDialog}

class WeightAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelWeightCsv).view
    path match {
      case Some( weightCsvPath ) =>
        val weights = transformEntities[Weight](weightCsvPath)
        val chart = WeightChart.build(weights)
        val chartPanel = buildChartPanel(chart)
        frame.addChartPanel(s"${Conf.titleWeight}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}