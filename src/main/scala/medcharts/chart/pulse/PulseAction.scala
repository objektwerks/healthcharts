package medcharts.chart.pulse

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.ChartAction
import medcharts.entity._
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