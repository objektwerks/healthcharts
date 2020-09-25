package medcharts.action

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.PulseChart
import medcharts.entity._
import medcharts.entity.Transformer._
import medcharts.ui.{Frame, PathDialog}

class PulseAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelPulseCsv).view
    path match {
      case Some( pulseCsvPath ) =>
        val pulses = transformEntities[Pulse](pulseCsvPath)
        val chart = PulseChart.build(pulses)
        val chartPanel = buildChartPanel(chart)
        frame.addChartPanel(s"${Conf.titlePulse}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}