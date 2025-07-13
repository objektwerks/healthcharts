package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Context
import healthchart.chart.GlucoseChart
import healthchart.entity.*
import healthchart.entity.Transformer.*
import healthchart.Logger.logError
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

final class GlucoseAction(name: String, frame: Frame) extends AbstractAction(name):
  val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Context.labelGlucoseCsv).view
    path match
      case Some( glucoseCsvPath ) =>
        val glucoses = transformEntities[Glucose](glucoseCsvPath)
        val chart = GlucoseChart.build(glucoses)
        val chartPanel = ChartPanelBuilder.build(chart, glucoses)
        frame.addCompositeChartPanel(s"${Context.titleGlucose}-${counter.getAndIncrement}", chartPanel)
      case None => logError(s"*** Path falled: $path")