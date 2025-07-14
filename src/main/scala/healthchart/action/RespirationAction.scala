package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Context
import healthchart.chart.RespirationChart
import healthchart.entity.*
import healthchart.entity.Transformer.*
import healthchart.Logger.logError
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

final class RespirationAction(name: String, frame: Frame) extends AbstractAction(name):
  private val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Context.labelRespirationCsv).view
    path match
      case Some( respirationCsvPath ) =>
        val respirations = transformEntities[Respiration](respirationCsvPath)
        val chart = RespirationChart.build(respirations)
        val chartPanel = ChartPanelBuilder.build(chart, respirations)
        frame.addCompositeChartPanel(s"${Context.titleRespiration}-${counter.getAndIncrement}", chartPanel)
      case None => logError(s"*** Path falled: $path")