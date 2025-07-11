package healthchart.ui

import java.awt.BorderLayout

import javax.swing.{JFrame, JPanel, WindowConstants}

import healthchart.Conf

final class Frame extends JFrame:
  val tabbedPane = TabbedPane()
  add(tabbedPane, BorderLayout.CENTER)

  setJMenuBar( MenuBar(this) )
  setTitle(Conf.appTitle)
  setSize(Conf.appWidth, Conf.appHeight)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setLocationRelativeTo(null)

  def addCompositeChartPanel(title: String, chartPanel: JPanel): Unit =
    tabbedPane.addCompositeChartPanel(title, chartPanel)
    revalidate()