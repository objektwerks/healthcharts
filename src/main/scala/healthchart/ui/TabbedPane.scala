package healthchart.ui

import java.awt.event.{MouseAdapter, MouseEvent}
import javax.swing.*

final class TabbedPane extends JTabbedPane:
  def addCompositeChartPanel(title: String, chartPanel: JPanel): Unit =
    addTab(null, chartPanel)
    setTabComponentAt( getTabCount - 1, CloseableTab(title, this, chartPanel) )
    setSelectedIndex( indexOfComponent(chartPanel) )

final class CloseableTab(title: String,
                         tabbedPane: JTabbedPane,
                         panel: JPanel) extends JPanel:
  private val closeLabel = JLabel(title)
  private val closeableLabel = JLabel("x")

  closeableLabel.addMouseListener( new MouseAdapter() {
    override def mouseEntered(event: MouseEvent): Unit =
      val color = closeableLabel.getBackground
      closeableLabel.setBackground(closeableLabel.getForeground)
      closeableLabel.setForeground(color)

    override def mouseExited(event: MouseEvent): Unit =
      val color = closeableLabel.getBackground
      closeableLabel.setBackground(closeableLabel.getForeground)
      closeableLabel.setForeground(color)

    override def mouseClicked(event: MouseEvent): Unit =
      tabbedPane.removeTabAt( tabbedPane.indexOfComponent(panel) )
    }
  )

  setOpaque(false)
  add(closeLabel)
  add(closeableLabel)