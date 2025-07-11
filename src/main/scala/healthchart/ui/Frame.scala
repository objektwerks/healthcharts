package healthchart.ui

import java.awt.{BorderLayout, Toolkit}

import javax.swing.{JFrame, JPanel, WindowConstants}

import healthchart.Context

final class Frame extends JFrame:
  val tabbedPane = TabbedPane()
  add(tabbedPane, BorderLayout.CENTER)

  setJMenuBar( MenuBar(this) )
  setTitle(Context.appTitle)
  setIconImage( Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")) )
  setSize(Context.appWidth, Context.appHeight)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setLocationRelativeTo(null)

  def addCompositeChartPanel(title: String, chartPanel: JPanel): Unit =
    tabbedPane.addCompositeChartPanel(title, chartPanel)
    revalidate()