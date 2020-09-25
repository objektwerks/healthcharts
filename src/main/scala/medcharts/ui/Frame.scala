package medcharts.ui

import java.awt.BorderLayout

import javax.swing.{JFrame, JPanel, WindowConstants}
import medcharts.Conf

class Frame extends JFrame {
  val tabbedPane = new TabbedPane()
  add(tabbedPane, BorderLayout.CENTER)

  setJMenuBar( new MenuBar(this) )
  setTitle(Conf.appTitle)
  setSize(Conf.appWidth, Conf.appHeight)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setLocationRelativeTo(null)

  def addCompositeChartPanel(title: String, chartPanel: JPanel): Unit = {
    tabbedPane.addCompositeChartPanel(title, chartPanel)
    revalidate()
  }
}