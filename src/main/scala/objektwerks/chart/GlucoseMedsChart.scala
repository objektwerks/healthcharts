package objektwerks.chart

 import java.awt.Color

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.chart.plot.PlotOrientation
import org.slf4j.LoggerFactory

import scala.util.Success
import scala.util.Failure

import Transformer._

object GlucoseMedsChart {
  private val logger = LoggerFactory.getLogger(GlucoseMedsChart.getClass)
  private val lineChart = 0
  private val scatterChart = 1

  def apply(): ChartPanel = Builder.build()

  def apply(glucoseCsvPath: String, medsCsvPath: String): ChartPanel = {
    val glucose = loadGlucoseCsv(glucoseCsvPath)
    val meds = loadMedsCsv(medsCsvPath)
    println(glucose)
    println(meds)
    Builder.build()
  }

  private def loadGlucoseCsv(path: String): (Array[Glucose], Array[InvalidLine]) = {
    csvToGlucose(path) match {
      case Success((lines, errors)) =>
        logLinesAndErrors( (lines, errors) )
        (lines, errors)
      case Failure(error) =>
        logger.error(s"glucose csv failure: ${error.printStackTrace()}")
        (Array.empty[Glucose], Array.empty[InvalidLine])
    }
  }

  private def loadMedsCsv(path: String): (Array[Med], Array[InvalidLine]) = {
    csvToMeds(path) match {
      case Success((lines, errors)) =>
        logLinesAndErrors( (lines, errors) )
        (lines, errors)
      case Failure(error) =>
        logger.error(s"meds csv failure: ${error.printStackTrace()}")
        (Array.empty[Med], Array.empty[InvalidLine])
    }
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
      val plot = chart.getXYPlot
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