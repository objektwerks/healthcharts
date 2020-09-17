package medcharts.chart

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.entity._
import medcharts.ui.{Frame, PathsDialog}

class GlucoseMedAction(name: String, frame: Frame) extends ChartAction(name) {
  private val title = Conf.titleGlucoseMed
  private val labelGlucoseCsv = Conf.labelGlucoseCsv
  private val labelMedCsv = Conf.labelMedCsv

  def actionPerformed(event: ActionEvent): Unit = {
    val paths = new PathsDialog(frame, labelGlucoseCsv, labelMedCsv).view
    paths match {
      case Some( (glucoseCsvPath, medCsvPath) ) =>
        val glucoses = transformEntities[Glucose](glucoseCsvPath)
        val meds = transformEntities[Med](medCsvPath)
        val chart = GlucoseMedChart.build(glucoses, meds)
        val chartPanel = buildChartPanel(chart)
        frame.addChart(s"$title-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}