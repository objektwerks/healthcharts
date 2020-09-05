package objektwerks.chart

import java.awt.event.ActionEvent

import javax.swing.AbstractAction
import java.util.concurrent.atomic.AtomicInteger

class GlucoseMedsAction(name: String, frame: Frame) extends AbstractAction(name) {
  private val title = Conf.glucoseMedsTitle
  private val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit = {
    val dialog = new GlucoseMedsDialog(frame)
    val (pathToGlucoseCsv, pathToMedsCsv) = dialog.view()
    if (pathToGlucoseCsv.nonEmpty && pathToMedsCsv.nonEmpty) {
      val chart = GlucoseMedsChart(glucoseCsvPath = pathToGlucoseCsv, medsCsvPath = pathToMedsCsv)
      frame.addChart(s"$title-${counter.getAndIncrement}", chart)
    }
  }
}