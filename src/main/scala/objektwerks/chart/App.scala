package objektwerks.chart

import java.awt.{BorderLayout, EventQueue}

import javax.swing.UIManager._

object App {
  def main(args: Array[String]): Unit = {
    EventQueue.invokeLater( new Runnable() {
      override def run(): Unit = {
        setLookAndFeel(getCrossPlatformLookAndFeelClassName)
        
        val frame = new Frame()

        // TODO: Show glucose.meds dialog.
        val chart = GlucoseMedsChart(glucoseCsvPath = "./data/glucose/glucose.txt", medsCsvPath = "./data/meds/meds.txt")
        frame.getContentPane.add(chart, BorderLayout.CENTER) // Temporary!

        frame.setVisible(true)
      }
    })
  }
}