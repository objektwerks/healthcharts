package objektwerks.chart

import java.awt.BorderLayout
import java.awt.event.ActionEvent

import javax.swing.AbstractAction

class GlucoseMedsAction(name: String, frame: Frame) extends AbstractAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val dialog = new GlucoseMedsDialog(frame)
    val (pathToGlucoseCsv, pathToMedsCsv) = dialog.view()
    if (pathToGlucoseCsv.nonEmpty && pathToMedsCsv.nonEmpty) {
      val chart = GlucoseMedsChart(glucoseCsvPath = pathToGlucoseCsv, medsCsvPath = pathToMedsCsv)
      frame.add(chart, BorderLayout.CENTER)
      frame.revalidate()
    }
  }
}