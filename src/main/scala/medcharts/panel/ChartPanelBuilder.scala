package medcharts.panel

import java.awt.{BorderLayout, Color}

import javax.swing.{BorderFactory, JPanel, JScrollPane, JTabbedPane, JTextArea, SwingConstants}
import medcharts.entity.Entities
import medcharts.entity.InvalidLine
import org.jfree.chart.{ChartPanel, JFreeChart}

import scala.reflect.ClassTag

object ChartPanelBuilder {
  def build[E: ClassTag](chart: JFreeChart, entities: Entities[E]): JPanel = {
    val compositePanel = new JPanel( new BorderLayout() )
    compositePanel.add( buildTabbedPane(chart, entities), BorderLayout.CENTER )
    compositePanel
  }

  def buildTabbedPane[E: ClassTag](chart: JFreeChart, entities: Entities[E]): JTabbedPane = {
    val tabbedPane = new JTabbedPane()
    tabbedPane.setTabPlacement( SwingConstants.BOTTOM )
    tabbedPane.addTab( "Chart", buildChartPanel(chart) )
    tabbedPane.addTab( "Entities", buildEntitiesPanel(entities.entities) )
    tabbedPane.addTab( "InvalidLines", buildInvalidLinesPanel(entities.invalidLines) )
    tabbedPane
  }

  def buildChartPanel(chart: JFreeChart): ChartPanel = {
    chart.getPlot.setBackgroundPaint(Color.LIGHT_GRAY)
    val chartPanel = new ChartPanel(chart)
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15))
    chartPanel.setInitialDelay(100)
    chartPanel.setReshowDelay(100)
    chartPanel.setDismissDelay(10000)
    chartPanel
  }

  def buildEntitiesPanel[E: ClassTag](entities: Array[E]): JPanel = {
    val panel = new JPanel( new BorderLayout() )
    val textArea = new JTextArea("")
    textArea.setEditable(false)
    for ( entity <- entities) textArea.append( entity.toString)
    val scrollPane = new JScrollPane(textArea)
    scrollPane.add(textArea)
    panel.add(scrollPane, BorderLayout.CENTER)
    panel
  }

  def buildInvalidLinesPanel(invalidLines: Array[InvalidLine]): JPanel = {
    val panel = new JPanel( new BorderLayout() )
    val textArea = new JTextArea("")
    textArea.setEditable(false)
    for ( line <- invalidLines) textArea.append( line.toString)
    val scrollPane = new JScrollPane(textArea)
    scrollPane.add(textArea)
    panel.add(scrollPane, BorderLayout.CENTER)
    panel
  }
}