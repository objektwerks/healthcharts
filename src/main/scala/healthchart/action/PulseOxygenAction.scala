package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.PulseOxygenChart
import healthchart.entity._
import healthchart.entity.Transformer._
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class PulseOxygenAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Conf.labelPulseOxygenCsv).view
    path match
      case Some( pulseOxygenCsvPath ) =>
        val pulseoxygens = transformEntities[PulseOxygen](pulseOxygenCsvPath)
        val chart = PulseOxygenChart.build(pulseoxygens)
        val chartPanel = ChartPanelBuilder.build(chart, pulseoxygens)
        frame.addCompositeChartPanel(s"${Conf.titlePulseOxygen}-${counter.getAndIncrement}", chartPanel)
      case None =>