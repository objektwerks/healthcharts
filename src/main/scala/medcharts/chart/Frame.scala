package medcharts.chart

import java.awt.BorderLayout

import javax.swing.{JFrame, WindowConstants}

import org.jfree.chart.ChartPanel

class Frame() extends JFrame {
  val tabbedPane = new TabbedPane()
  add(tabbedPane, BorderLayout.CENTER)

  setJMenuBar( new MenuBar(this) )
  setTitle(Conf.title)
  setSize(Conf.width, Conf.height)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setLocationRelativeTo(null)

  def addChart(title: String, chart: ChartPanel): Unit = {
    tabbedPane.addChart(title, chart)
    revalidate()
  }
}