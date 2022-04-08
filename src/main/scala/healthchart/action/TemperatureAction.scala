package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.TemperatureChart
import healthchart.entity._
import healthchart.entity.Transformer._
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class TemperatureAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Conf.labelTemperatureCsv).view
    path match
      case Some( temperatureCsvPath ) =>
        val temperatures = transformEntities[Temperature](temperatureCsvPath)
        val chart = TemperatureChart.build(temperatures)
        val chartPanel = ChartPanelBuilder.build(chart, temperatures)
        frame.addCompositeChartPanel(s"${Conf.titleTemperature}-${counter.getAndIncrement}", chartPanel)
      case None =>