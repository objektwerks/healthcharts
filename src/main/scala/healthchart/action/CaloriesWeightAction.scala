package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.CaloriesWeightChart
import healthchart.entity.Transformer._
import healthchart.entity._
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class CaloriesWeightAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Conf.labelCaloriesWeightCsv).view
    path match
      case Some( caloriesWeightCsvPath ) =>
        val caloriesWeights = transformEntities[CaloriesWeight](caloriesWeightCsvPath)
        val chart = CaloriesWeightChart.build(caloriesWeights)
        val chartPanel = ChartPanelBuilder.build(chart, caloriesWeights)
        frame.addCompositeChartPanel(s"${Conf.titleCaloriesWeight}-${counter.getAndIncrement}", chartPanel)
      case None =>