package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Context
import healthchart.chart.PulseChart
import healthchart.entity.*
import healthchart.entity.Transformer.*
import healthchart.Logger.logError
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

final class PulseAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Context.labelPulseCsv).view
    path match
      case Some( pulseCsvPath ) =>
        val pulses = transformEntities[Pulse](pulseCsvPath)
        val chart = PulseChart.build(pulses)
        val chartPanel = ChartPanelBuilder.build(chart, pulses)
        frame.addCompositeChartPanel(s"${Context.titlePulse}-${counter.getAndIncrement}", chartPanel)
      case None => logError(s"*** Path falled: $path")