package medcharts.ui

import java.awt.event.{MouseAdapter, MouseEvent}

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
  private val closeableLabel = new JLabel("x")

  addMouseListener( new MouseAdapter() {
    override def mouseEntered(event: MouseEvent): Unit = {
      val color = closeableLabel.getBackground
      closeableLabel.setBackground(closeableLabel.getForeground)
      closeableLabel.setForeground(color)
    }
    override def mouseExited(event: MouseEvent): Unit = {
      val color = closeableLabel.getBackground
      closeableLabel.setBackground(closeableLabel.getForeground)
      closeableLabel.setForeground(color)
    }
    override def mouseClicked(e: MouseEvent): Unit = {
      tabbedPane.removeTabAt( tabbedPane.indexOfComponent(panel) )
    }
  })

  setOpaque(false)
  add(closeLabel)
  add(closeableLabel)
}