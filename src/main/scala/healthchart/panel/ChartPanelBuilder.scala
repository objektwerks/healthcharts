package healthchart.panel

import java.awt.{BorderLayout, Color}
import javax.swing.{BorderFactory, JPanel, JScrollPane, JTabbedPane, JTextArea, SwingConstants}

import org.jfree.chart.{ChartPanel, JFreeChart}

import healthchart.Context
import healthchart.entity.Entities
import healthchart.entity.InvalidEntity

object ChartPanelBuilder:
  def build[E](chart: JFreeChart, entities: Entities[E]): JPanel =
    val compositePanel = JPanel( BorderLayout() )
    compositePanel.add( buildTabbedPane(chart, entities), BorderLayout.CENTER )
    compositePanel

  def buildTabbedPane[E](chart: JFreeChart, entities: Entities[E]): JTabbedPane =
    val tabbedPane = JTabbedPane()
    tabbedPane.setTabPlacement( SwingConstants.BOTTOM )
    tabbedPane.addTab( Context.tabChart, buildChartPanel(chart) )
    tabbedPane.addTab( Context.tabEntities, buildEntitiesPanel(entities.entities) )
    tabbedPane.addTab( Context.tabInvalidEntities, buildInvalidEntitiesPanel(entities.invalidEntities) )
    tabbedPane

  def buildChartPanel(chart: JFreeChart): ChartPanel =
    chart.getPlot.setBackgroundPaint(Color.LIGHT_GRAY)
    val chartPanel = ChartPanel(chart)
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15))
    chartPanel.setInitialDelay(100)
    chartPanel.setReshowDelay(100)
    chartPanel.setDismissDelay(10000)
    chartPanel

  def buildEntitiesPanel[E](entities: Array[E]): JPanel =
    val textArea = JTextArea()
    textArea.setBackground(Color.lightGray)
    textArea.setEditable(false)
    if (entities.nonEmpty)
      for ( entity <- entities ) textArea.append( s"${entity.toString}\n" )
    else textArea.append("0")
    val scrollPane = JScrollPane(textArea)
    val panel = JPanel( BorderLayout() )
    panel.add(scrollPane, BorderLayout.CENTER )
    panel

  def buildInvalidEntitiesPanel(invalidEntities: Array[InvalidEntity]): JPanel =
    val textArea = JTextArea()
    textArea.setBackground(Color.lightGray)
    textArea.setEditable(false)
    if (invalidEntities.nonEmpty)
      for ( invalidEntity <- invalidEntities) textArea.append( s"${invalidEntity.toString}\n" )
    else textArea.append("0")
    val scrollPane = JScrollPane(textArea)
    val panel = JPanel( BorderLayout() )
    panel.add(scrollPane, BorderLayout.CENTER )
    panel