package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Context
import healthchart.chart.VitalsChart
import healthchart.entity.*
import healthchart.entity.Transformer.*
import healthchart.Logger.logError
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

final class VitalsAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Context.labelVitalsCsv).view
    path match
      case Some( vitalsCsvPath ) =>
        val vitals = transformEntities[Vitals](vitalsCsvPath)
        val chart = VitalsChart.build(vitals)
        val chartPanel = ChartPanelBuilder.build(chart, vitals)
        frame.addCompositeChartPanel(s"${Context.titleVitals}-${counter.getAndIncrement}", chartPanel)
      case None => logError(s"*** Path falled: $path")