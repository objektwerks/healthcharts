package objektwerks.chart

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener}

import javax.swing._

import org.jfree.chart.ChartPanel

class TabbedPane extends JTabbedPane {
  def addChart(title: String, chart: ChartPanel): Unit = {
    addTab(null, chart)
    setTabComponentAt( getTabCount - 1, new CloseableTab(title, this, chart) )
    setSelectedIndex( indexOfComponent(chart) )
  }
}

class CloseableTab(title: String, tabbedPane: JTabbedPane, panel: JPanel) extends JPanel {
  val closeLabel = new JLabel(title)
  val closeButton = new JButton("X")
  closeButton.addActionListener( new ActionListener() {
    override def actionPerformed(event: ActionEvent): Unit = {
      val tabIndex = tabbedPane.indexOfComponent(panel)
      tabbedPane.removeTabAt(tabIndex)
    }
  })
  setOpaque(false)
  add(closeLabel, BorderLayout.WEST)
  add(closeButton, BorderLayout.EAST)
}