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

object WeightChart extends Chart:
  def build(weights: Entities[Weight]): JFreeChart =
    val xyPlot = XYPlot()
    xyPlot.setDataset( buildWeightDataset(weights) )
    xyPlot.setRenderer( buildWeightRenderer() )

    val xAxis = DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(xAxis)

    val yAxis = NumberAxis(Context.titleWeightChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titleWeight, weights.toEntity)
    JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildWeightDataset(weights: Entities[Weight]): XYDataset =
    val timeSeries = TimeSeries(Context.titleWeight)
    weights.entities.foreach { weight =>
      timeSeries.add( weight.datetime, weight.pounds )
    }
    TimeSeriesCollection(timeSeries)

  def buildWeightRenderer(): XYItemRenderer =
    val renderer = XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = SimpleDateFormat("d,H:m").format( jdate.Date( xValue.toLong ) )
        val pounds = DecimalFormat("0.0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $pounds, $delta%)"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0.0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer