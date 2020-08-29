package objektwerks.chart

import java.awt.{BorderLayout, EventQueue}

import javax.swing.{JFrame, WindowConstants}

object App {
  val centerWindow = null
  def main(args: Array[String]): Unit = {
    EventQueue.invokeLater( new Runnable() {
      override def run(): Unit = {
        // TODO: Show file dialog for both paths.
        val chart = GlucoseMedsChart(glucoseCsvPath = "./data/glucose/glucose.txt", medsCsvPath = "./data/meds/meds.txt")
        val frame = new JFrame()
        frame.getContentPane.add(chart, BorderLayout.CENTER)
        frame.setTitle("MedCharts")
        frame.setSize(900, 600)
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
        frame.setLocationRelativeTo(centerWindow)
        frame.setVisible(true);
      }
    })
  }
}