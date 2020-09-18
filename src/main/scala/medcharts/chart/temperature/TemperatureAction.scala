package medcharts.chart.temperature

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.ChartAction
import medcharts.entity._
import medcharts.ui.{Frame, PathDialog}

class TemperatureAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelTemperatureCsv).view
    path match {
      case Some( temperatureCsvPath ) =>
        val temperatures = transformEntities[Temperature](temperatureCsvPath)
        val chart = TemperatureChart.build(temperatures)
        val chartPanel = buildChartPanel(chart)
        frame.addChart(s"${Conf.titleTemperature}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}