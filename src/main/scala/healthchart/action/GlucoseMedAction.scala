package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.GlucoseMedChart
import healthchart.entity.*
import healthchart.entity.Transformer.*
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class GlucoseMedAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Conf.labelGlucoseMedCsv).view
    path match
      case Some( glucosemedCsvPath ) =>
        val glucosemeds = transformEntities[GlucoseMed](glucosemedCsvPath)
        val chart = GlucoseMedChart.build(glucosemeds)
        val chartPanel = ChartPanelBuilder.build(chart, glucosemeds)
        frame.addCompositeChartPanel(s"${Conf.titleGlucoseMed}-${counter.getAndIncrement}", chartPanel)
      case None =>