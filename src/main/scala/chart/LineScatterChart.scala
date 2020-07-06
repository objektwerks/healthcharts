package chart

import java.awt.Color

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.chart.plot.PlotOrientation

object LineScatterChart {
  private val lineChart = 0
  private val scatterChart = 1

  def apply(): ChartPanel = Chart.build()

  private object Chart {
    def build(): ChartPanel = {
      val chartTitle = "Line-Scatter Chart"
      val xAxisLabel = "Domain"
      val yAxisLabel = "Range"
      val dataset = buildDataset()
      val chart = ChartFactory.createXYLineChart(
        chartTitle,
        xAxisLabel,
        yAxisLabel,
        dataset,
        PlotOrientation.VERTICAL,
        true,  // legend
        true,  // tooltips
        false) // urls
      val plot = chart.getXYPlot()
      val renderer = new XYLineAndShapeRenderer()
      renderer.setSeriesLinesVisible(lineChart, true)
      renderer.setSeriesShapesVisible(lineChart, true)
      renderer.setSeriesLinesVisible(scatterChart, false)
      renderer.setSeriesShapesVisible(scatterChart, true)
      plot.setBackgroundPaint(Color.DARK_GRAY)
      plot.setRenderer(renderer)
      new ChartPanel(chart)
    }

    def buildDataset(): XYDataset = {
      val lineSeries = new XYSeries("Line Series")
      lineSeries.add(1, 2)
      lineSeries.add(3, 4)
      lineSeries.add(5, 6)
      lineSeries.add(7, 8)

      val scatterSeries = new XYSeries("Scatter Series")
      scatterSeries.add(2, 1)
      scatterSeries.add(4, 3)
      scatterSeries.add(6, 5)
      scatterSeries.add(8, 7)

      val dataset = new XYSeriesCollection()
      dataset.addSeries(lineSeries)
      dataset.addSeries(scatterSeries)
      dataset
    }
  }
}