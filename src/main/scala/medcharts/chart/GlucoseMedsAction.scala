package medcharts.chart

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

import medcharts.Conf
import medcharts.ui.Frame

class GlucoseMedsAction(name: String, frame: Frame) extends AbstractAction(name) {
  private val title = Conf.titleGlucoseMeds
  private val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val dialog = new GlucoseMedsDialog(frame)
    val (pathToGlucoseCsv, pathToMedsCsv) = dialog.view()
    if (pathToGlucoseCsv.nonEmpty && pathToMedsCsv.nonEmpty) {
      val chart = GlucoseMedsChart(glucoseCsvPath = pathToGlucoseCsv, medsCsvPath = pathToMedsCsv)
      frame.addChart( s"$title-${counter.getAndIncrement}", chart )
    }
  }
}