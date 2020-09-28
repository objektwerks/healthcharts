package medcharts.ui

import java.awt.event.{MouseAdapter, MouseEvent}

import javax.swing._

class TabbedPane extends JTabbedPane {
  def addCompositeChartPanel(title: String, chartPanel: JPanel): Unit = {
    addTab(null, chartPanel)
    setTabComponentAt( getTabCount - 1, new CloseableTab(title, this, chartPanel) )
    setSelectedIndex( indexOfComponent(chartPanel) )
  }
}

class CloseableTab(title: String, tabbedPane: JTabbedPane, panel: JPanel) extends JPanel {
  private val closeLabel = new JLabel(title)
  private val closeableLabel = new JLabel("x")

  closeableLabel.addMouseListener( new MouseAdapter() {
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
    override def mouseClicked(event: MouseEvent): Unit = {
      tabbedPane.removeTabAt( tabbedPane.indexOfComponent(panel) )
    }
  })

  setOpaque(false)
  add(closeLabel)
  add(closeableLabel)
}