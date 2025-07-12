package healthchart.ui

import java.awt.BorderLayout
import javax.swing.{JFrame, JPanel, WindowConstants}

import healthchart.Context

class Frame extends JFrame:
  val tabbedPane = TabbedPane()
  add(TabbedPane(), BorderLayout.CENTER)

  setJMenuBar(MenuBar(this))
  setTitle(Context.appTitle)
  setSize(Context.appWidth, Context.appHeight)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setLocationRelativeTo(null)

  def addCompositeChartPanel(title: String, chartPanel: JPanel): Unit =
    tabbedPane.addCompositeChartPanel(title, chartPanel)
    revalidate()