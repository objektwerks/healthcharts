package medcharts.action

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.GlucoseMedChart
import medcharts.entity._
import medcharts.entity.Transformer._
import medcharts.panel.ChartPanelBuilder
import medcharts.ui.{Frame, PathsDialog}

class GlucoseMedAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val paths = new PathsDialog(frame, Conf.labelGlucoseCsv, Conf.labelMedCsv).view
    paths match {
      case Some( (glucoseCsvPath, medCsvPath) ) =>
        val glucoses = transformEntities[Glucose](glucoseCsvPath)
        val meds = transformEntities[Med](medCsvPath)
        val chart = GlucoseMedChart.build(glucoses, meds)
        val chartPanel = ChartPanelBuilder.build(chart, glucoses) // TODO add meds!
        frame.addCompositeChartPanel(s"${Conf.titleGlucoseMed}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}