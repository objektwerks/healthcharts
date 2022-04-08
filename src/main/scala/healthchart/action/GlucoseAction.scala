package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.GlucoseChart
import healthchart.entity._
import healthchart.entity.Transformer._
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class GlucoseAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Conf.labelGlucoseCsv).view
    path match
      case Some( glucoseCsvPath ) =>
        val glucoses = transformEntities[Glucose](glucoseCsvPath)
        val chart = GlucoseChart.build(glucoses)
        val chartPanel = ChartPanelBuilder.build(chart, glucoses)
        frame.addCompositeChartPanel(s"${Conf.titleGlucose}-${counter.getAndIncrement}", chartPanel)
      case None =>