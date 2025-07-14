package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Context
import healthchart.chart.CaloriesWeightChart
import healthchart.entity.Transformer.*
import healthchart.entity.*
import healthchart.Logger.logError
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

final class CaloriesWeightAction(name: String, frame: Frame) extends AbstractAction(name):
  private val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Context.labelCaloriesWeightCsv).view
    path match
      case Some( caloriesWeightCsvPath ) =>
        val caloriesWeights = transformEntities[CaloriesWeight](caloriesWeightCsvPath)
        val chart = CaloriesWeightChart.build(caloriesWeights)
        val chartPanel = ChartPanelBuilder.build(chart, caloriesWeights)
        frame.addCompositeChartPanel(s"${Context.titleCaloriesWeight}-${counter.getAndIncrement}", chartPanel)
      case None => logError(s"*** Path falled: $path")