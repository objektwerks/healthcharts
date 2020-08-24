package objektwerks.chart

import java.awt.BorderLayout
import java.awt.EventQueue

import javax.swing.JFrame
import javax.swing.WindowConstants

object App {
  val centerWindow = null
  def main(args: Array[String]): Unit = {
    EventQueue.invokeLater( new Runnable() {
      override def run(): Unit = {
        val chart = GlucoseMedsChart(glucoseCsvPath = "./data/glucose/glucose.txt",
                                     medsCsvPath = "./data/meds/meds.txt")
        val frame = new JFrame()
        frame.add(chart, BorderLayout.CENTER)
        frame.setTitle("MedCharts")
        frame.setSize(640, 480)
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        frame.setLocationRelativeTo(centerWindow)
        frame.setVisible(true);
      }
    })
  }
}