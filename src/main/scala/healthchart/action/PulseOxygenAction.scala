package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Context
import healthchart.chart.PulseOxygenChart
import healthchart.entity.*
import healthchart.entity.Transformer.*
import healthchart.Logger.logError
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

final class PulseOxygenAction(name: String, frame: Frame) extends AbstractAction(name):
  private val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Context.labelPulseOxygenCsv).view
    path match
      case Some( pulseOxygenCsvPath ) =>
        val pulseoxygens = transformEntities[PulseOxygen](pulseOxygenCsvPath)
        val chart = PulseOxygenChart.build(pulseoxygens)
        val chartPanel = ChartPanelBuilder.build(chart, pulseoxygens)
        frame.addCompositeChartPanel(s"${Context.titlePulseOxygen}-${counter.getAndIncrement}", chartPanel)
      case None => logError(s"*** Path falled: $path")