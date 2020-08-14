package objektwerks.chart

 import java.awt.Color

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.chart.plot.PlotOrientation

final case class GlucoseMedsChart(glucose: (Array[Glucose], Array[InvalidLine]),
                                  meds: (Array[Med], Array[InvalidLine]))
// TODO: Refactor into overlaid chart!
object GlucoseMedsChart {
  private val lineChart = 0
  private val scatterChart = 1

  def apply(): ChartPanel = Builder.build()

  def apply(chart: GlucoseMedsChart): ChartPanel = {
    println(chart)
    Builder.build()
  }

  private object Builder {
    def build(): ChartPanel = {
      val chartTitle = "Glucose-Meds Chart"
      val xAxisLabel = "Domain"
      val yAxisLabel = "Range"
      val xySeries = buildXYSeriesCollection()
      val chart = ChartFactory.createXYLineChart(
        chartTitle,
        xAxisLabel,
        yAxisLabel,
        xySeries,
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

    def buildXYSeriesCollection(): XYSeriesCollection = {
      val lineSeries = new XYSeries("Line Series")
      lineSeries.add(1, 2)
      lineSeries.add(3, 4)
      lineSeries.add(5, 6)
      lineSeries.add(7, 8)
      lineSeries.add(9, 10)
      lineSeries.add(11, 12)
      lineSeries.add(13, 14)
      lineSeries.add(15, 16)

      val scatterSeries = new XYSeries("Scatter Series")
      scatterSeries.add(2, 1)
      scatterSeries.add(4, 3)
      scatterSeries.add(6, 5)
      scatterSeries.add(8, 7)
      scatterSeries.add(10, 9)
      scatterSeries.add(12, 11)
      scatterSeries.add(14, 13)
      scatterSeries.add(16, 15)

      val xySeries = new XYSeriesCollection()
      xySeries.addSeries(lineSeries)
      xySeries.addSeries(scatterSeries)
      xySeries
    }
  }
}