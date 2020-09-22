package medcharts.chart.pulseoxygen

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.ChartAction
import medcharts.entity._
import medcharts.ui.{Frame, PathDialog}

class PulseOxygenAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelPulseOxygenCsv).view
    path match {
      case Some( pulseOxygenCsvPath ) =>
        val pulseoxygens = transformEntities[PulseOxygen](pulseOxygenCsvPath)
        val chart = PulseOxygenChart.build(pulseoxygens)
        val chartPanel = buildChartPanel(chart)
        frame.addChartPanel(s"${Conf.titlePulseOxygen}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}