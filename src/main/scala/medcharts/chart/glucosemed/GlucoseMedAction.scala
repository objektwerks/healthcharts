package medcharts.chart.glucosemed

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.ChartAction
import medcharts.entity._
import medcharts.ui.{Frame, PathsDialog}

class GlucoseMedAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val paths = new PathsDialog(frame, Conf.labelGlucoseCsv, Conf.labelMedCsv).view
    paths match {
      case Some( (glucoseCsvPath, medCsvPath) ) =>
        val glucoses = transformEntities[Glucose](glucoseCsvPath)
        val meds = transformEntities[Med](medCsvPath)
        val chart = GlucoseMedChart.build(glucoses, meds)
        val chartPanel = buildChartPanel(chart)
        frame.addChart(s"${Conf.titleGlucoseMed}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}