package medcharts.chart

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.entity._
import medcharts.ui.Frame

class GlucoseMedsAction(name: String, frame: Frame) extends ChartAction(name, frame) {
  private val title = Conf.titleGlucoseMeds

  def actionPerformed(event: ActionEvent): Unit = {
    val (glucoseCsvPath, medsCsvPath) = new PathsDialog(frame, Conf.labelGlucoseCsv, Conf.labelMedsCsv).view
    val glucoses = transformEntities[Glucose](glucoseCsvPath)
    val meds = transformEntities[Med](medsCsvPath)

    if (glucoses.nonEmpty && meds.nonEmpty) {
      val chart = GlucoseMedsChart.build(glucoses, meds)
      frame.addChart( s"$title-${counter.getAndIncrement}", chart )
    } else {
      val message = s"Glucoses = ${glucoses.entities.length} : Meds = ${meds.entities.length}"
      showEntitiesErrorDialog(message)
    }
  }
}