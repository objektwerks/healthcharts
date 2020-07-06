package chart

import java.awt.Color

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection

object LineChart {
  def apply(): ChartPanel = Chart.build()

  private object Chart {
    def build(): ChartPanel = {
      val chartTitle = "Line Chart"
      val xAxisLabel = "Domain"
      val yAxisLabel = "Range"
      val dataset = buildDataset()
      val chart = ChartFactory.createXYLineChart(
        chartTitle,
        xAxisLabel,
        yAxisLabel,
        dataset)
      val plot = chart.getXYPlot()
      val renderer = new XYLineAndShapeRenderer()
      plot.setRenderer(renderer)
      plot.setBackgroundPaint(Color.DARK_GRAY)
      new ChartPanel(chart)
    }

    def buildDataset(): XYDataset = {
      val dataset = new XYSeriesCollection()
      val series1 = new XYSeries("1")
      val series2 = new XYSeries("2")
      val series3 = new XYSeries("3")
    
      series1.add(1.0, 2.0)
      series1.add(2.0, 3.0)
      series1.add(3.0, 2.5)
      series1.add(3.5, 2.8)
      series1.add(4.2, 6.0)
    
      series2.add(2.0, 1.0)
      series2.add(2.5, 2.4)
      series2.add(3.2, 1.2)
      series2.add(3.9, 2.8)
      series2.add(4.6, 3.0)
    
      series3.add(1.2, 4.0)
      series3.add(2.5, 4.4)
      series3.add(3.8, 4.2)
      series3.add(4.3, 3.8)
      series3.add(4.5, 4.0)
    
      dataset.addSeries(series1)
      dataset.addSeries(series2)
      dataset.addSeries(series3)
      dataset
    }
  }
}