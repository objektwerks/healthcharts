package objektwerks.chart

import java.awt.{BorderLayout, EventQueue}

import javax.swing.{JFrame, JMenu, JMenuItem, JMenuBar, WindowConstants}

object App {
  val centerWindow = null
  def main(args: Array[String]): Unit = {
    EventQueue.invokeLater( new Runnable() {
      override def run(): Unit = {
        // TODO: Show file dialog for both paths.
        val chart = GlucoseMedsChart(glucoseCsvPath = "./data/glucose/glucose.txt", medsCsvPath = "./data/meds/meds.txt")
        
        val menubar = new JMenuBar()
        val menu = new JMenu("Charts")
        val menuItem = new JMenuItem("Glucose-Meds")
        menu.add(menuItem)
        menubar.add(menu)
        
        val frame = new JFrame()
        frame.setJMenuBar(menubar)
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