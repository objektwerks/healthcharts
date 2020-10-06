package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.VitalsChart
import healthchart.entity._
import healthchart.entity.Transformer._
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class VitalsAction(name: String, frame: Frame) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelVitalsCsv).view
    path match {
      case Some( vitalsCsvPath ) =>
        val vitals = transformEntities[Vitals](vitalsCsvPath)
        val chart = VitalsChart.build(vitals)
        val chartPanel = ChartPanelBuilder.build(chart, vitals)
        frame.addCompositeChartPanel(s"${Conf.titleVitals}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}