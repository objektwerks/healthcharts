package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.RespirationChart
import healthchart.entity._
import healthchart.entity.Transformer._
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class RespirationAction(name: String, frame: Frame) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelRespirationCsv).view
    path match {
      case Some( respirationCsvPath ) =>
        val respirations = transformEntities[Respiration](respirationCsvPath)
        val chart = RespirationChart.build(respirations)
        val chartPanel = ChartPanelBuilder.build(chart, respirations)
        frame.addCompositeChartPanel(s"${Conf.titleRespiration}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}