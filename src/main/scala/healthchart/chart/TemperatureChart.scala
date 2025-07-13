package healthchart.chart

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset


import healthchart.Context
import healthchart.entity.*

object TemperatureChart extends Chart:
  def build(temperatures: Entities[Temperature]): JFreeChart =
    val xyPlot = XYPlot()
    xyPlot.setDataset( buildTemperatureDataset(temperatures) )
    xyPlot.setRenderer( buildTemperatureRenderer() )

    val xAxis = DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(xAxis)

    val yAxis = NumberAxis(Context.titleTemperatureChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titleTemperature, temperatures.toEntity)
    JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildTemperatureDataset(temperatures: Entities[Temperature]): XYDataset =
    val timeSeries = TimeSeries(Context.titleTemperature)
    temperatures.entities.foreach { weight =>
      timeSeries.add( weight.datetime, weight.degrees )
    }
    TimeSeriesCollection(timeSeries)

  def buildTemperatureRenderer(): XYItemRenderer =
    val renderer = XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = SimpleDateFormat("d,H:m").format( jdate.Date( xValue.toLong ) )
        val degrees = DecimalFormat("0.0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Context.titleTemperature}: ($dayHourMinute, $degrees, $delta%)"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0.0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer