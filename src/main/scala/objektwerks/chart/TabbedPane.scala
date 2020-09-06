package objektwerks.chart

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener}

import javax.swing._

import org.jfree.chart.ChartPanel

class TabbedPane extends JTabbedPane {
  def addChart(title: String, chart: ChartPanel): Unit = {
    addTab(null, chart)
    setTabComponentAt( getTabCount - 1, new CloseableTab(this, title, chart) )
    setSelectedIndex( indexOfTab(title) )
  }
}

class CloseableTab(tabbedPane: TabbedPane, title: String, chart: ChartPanel) extends JPanel {
  val label = new JLabel(title)
  val closeButton = new JButton("X")
  closeButton.addActionListener( new ActionListener() {
    override def actionPerformed(event: ActionEvent): Unit = {
      val tabIndex = tabbedPane.indexOfComponent(chart)
      tabbedPane.removeTabAt(tabIndex)
    }
  })
  setOpaque(false)
  add(label, BorderLayout.WEST)
  add(closeButton, BorderLayout.EAST)
}