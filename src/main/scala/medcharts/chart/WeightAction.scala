package medcharts.chart

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.entity._
import medcharts.ui.{Frame, PathDialog}

class WeightAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val paths = new PathDialog(frame, Conf.labelWeightCsv).view
    paths match {
      case Some( path ) =>
        val weights = transformEntities[Weight](path)
        val chart = WeightChart.build(weights)
        val chartPanel = buildChartPanel(chart)
        frame.addChart(s"${Conf.titleWeight}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}