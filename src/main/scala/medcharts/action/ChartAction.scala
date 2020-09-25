package medcharts.action

import java.awt.Color
import java.util.concurrent.atomic.AtomicInteger

import javax.swing.{AbstractAction, BorderFactory}

import org.jfree.chart.{ChartPanel, JFreeChart}

abstract class ChartAction(name: String) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)

  protected def buildChartPanel(chart: JFreeChart): ChartPanel = {
    chart.getPlot.setBackgroundPaint(Color.LIGHT_GRAY)
    val chartPanel = new ChartPanel(chart)
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15))
    chartPanel.setInitialDelay(100)
    chartPanel.setReshowDelay(100)
    chartPanel.setDismissDelay(10000)
    chartPanel
  }
}