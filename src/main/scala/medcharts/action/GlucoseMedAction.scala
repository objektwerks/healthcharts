package medcharts.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import medcharts.Conf
import medcharts.chart.GlucoseMedChart
import medcharts.entity._
import medcharts.entity.Transformer._
import medcharts.panel.ChartPanelBuilder
import medcharts.ui.{Frame, PathsDialog}

class GlucoseMedAction(name: String, frame: Frame) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val paths = new PathsDialog(frame, Conf.labelGlucoseCsv, Conf.labelMedCsv).view
    paths match {
      case Some( (glucoseCsvPath, medCsvPath) ) =>
        val glucoses = transformEntities[Glucose](glucoseCsvPath)
        val meds = transformEntities[Med](medCsvPath)
        val chart = GlucoseMedChart.build(glucoses, meds)
        val chartPanel = ChartPanelBuilder.build(chart, glucoses) // TODO: combine glucoses and meds!
        frame.addCompositeChartPanel(s"${Conf.titleGlucoseMed}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}