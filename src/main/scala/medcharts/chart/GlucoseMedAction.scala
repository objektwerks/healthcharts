package medcharts.chart

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.entity._
import medcharts.ui.{Frame, PathsDialog}

class GlucoseMedAction(name: String, frame: Frame) extends ChartAction(name, frame) {
  private val title = Conf.titleGlucoseMed
  private val labelGlucoseCsv = Conf.labelGlucoseCsv
  private val labelMedCsv = Conf.labelMedCsv

  def actionPerformed(event: ActionEvent): Unit = {
    val (wasNotCancelled, glucoseCsvPath, medCsvPath) = new PathsDialog(frame, labelGlucoseCsv, labelMedCsv).view

    if (wasNotCancelled && glucoseCsvPath.nonEmpty && medCsvPath.nonEmpty) {
      val glucoses = transformEntities[Glucose](glucoseCsvPath)
      val meds = transformEntities[Med](medCsvPath)
      val chart = GlucoseMedChart.build(glucoses, meds)
      val chartPanel = buildChartPanel(chart)
      frame.addChart( s"$title-${counter.getAndIncrement}", chartPanel )
    } else if (wasNotCancelled) {
      val glucoseCount = transformEntities[Glucose](glucoseCsvPath).count
      val medCount = transformEntities[Med](medCsvPath).count
      val message = s"Glucose count = $glucoseCount : Med count = $medCount"
      showEntitiesErrorDialog(message)
    }
  }
}