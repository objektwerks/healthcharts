package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Context
import healthchart.chart.MedChart
import healthchart.entity.Transformer.*
import healthchart.entity.*
import healthchart.Logger.logError
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

final class MedAction(name: String, frame: Frame) extends AbstractAction(name):
  val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Context.labelMedCsv).view
    path match
      case Some( medCsvPath ) =>
        val meds = transformEntities[Med](medCsvPath)
        val chart = MedChart.build(meds)
        val chartPanel = ChartPanelBuilder.build(chart, meds)
        frame.addCompositeChartPanel(s"${Context.titleMed}-${counter.getAndIncrement}", chartPanel)
      case None => logError(s"*** Path falled: $path")