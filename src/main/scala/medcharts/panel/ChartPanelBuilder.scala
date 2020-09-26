package medcharts.panel

import java.awt.{BorderLayout, Color}

import javax.swing.{BorderFactory, JPanel, JScrollPane, JTabbedPane, JTextArea, SwingConstants}
import medcharts.Conf
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
    tabbedPane.addTab( Conf.tabChart, buildChartPanel(chart) )
    tabbedPane.addTab( Conf.tabEntities, buildEntitiesPanel(entities.entities) )
    tabbedPane.addTab( Conf.tabInvalidLines, buildInvalidLinesPanel(entities.invalidLines) )
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
    val textArea = new JTextArea()
    textArea.setBackground(Color.lightGray)
    textArea.setEditable(false)
    if (entities.nonEmpty)
      for ( entity <- entities ) textArea.append( s"${entity.toString}\n" )
    else textArea.append("0")
    val scrollPane = new JScrollPane(textArea)
    val panel = new JPanel( new BorderLayout() )
    panel.add(scrollPane, BorderLayout.CENTER )
    panel
  }

  def buildInvalidLinesPanel(invalidLines: Array[InvalidLine]): JPanel = {
    val textArea = new JTextArea()
    textArea.setBackground(Color.lightGray)
    textArea.setEditable(false)
    if (invalidLines.nonEmpty)
      for ( line <- invalidLines ) textArea.append( s"${line.toString}\n" )
    else textArea.append("0")
    val scrollPane = new JScrollPane(textArea)
    val panel = new JPanel( new BorderLayout() )
    panel.add(scrollPane, BorderLayout.CENTER )
    panel
  }
}