package objektwerks.chart

import java.awt.BorderLayout
import java.awt.event.ActionEvent

import javax.swing.AbstractAction

class GlucoseMedsAction(name: String, frame: Frame) extends AbstractAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val dialog = new GlucoseMedsDialog(frame)
    val (pathToGlucoseCsv, pathToMedsCsv) = dialog.view()
    println(pathToGlucoseCsv) // Todo
    println(pathToMedsCsv) // Todo
    val chart = GlucoseMedsChart(glucoseCsvPath = "./data/glucose/glucose.txt", medsCsvPath = "./data/meds/meds.txt")
    frame.getContentPane.add(chart, BorderLayout.CENTER)
  }
}