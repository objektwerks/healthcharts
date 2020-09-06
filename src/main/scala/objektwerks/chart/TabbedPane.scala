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
  private val closeLabel = new JLabel(title)
  private val closeButton = new JButton("X")
  closeButton.addActionListener( new ActionListener() {
    override def actionPerformed(event: ActionEvent): Unit = {
      tabbedPane.removeTabAt( tabbedPane.indexOfComponent(panel) )
    }
  })
  setOpaque(false)
  add(closeLabel, BorderLayout.WEST)
  add(closeButton, BorderLayout.EAST)
}