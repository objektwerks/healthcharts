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

object PulseChart extends Chart:
  def build(pulses: Entities[Pulse]): JFreeChart =
    val xyPlot = XYPlot()
    xyPlot.setDataset( buildPulseDataset(pulses) )
    xyPlot.setRenderer( buildPulseRenderer() )

    val xAxis = DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(xAxis)

    val yAxis = NumberAxis(Context.titlePulseChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titlePulse, pulses.toEntity)
    JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildPulseDataset(pulses: Entities[Pulse]): XYDataset =
    val timeSeries = TimeSeries(Context.titlePulse)
    pulses.entities.foreach { pulse =>
      timeSeries.add( pulse.datetime, pulse.beatsPerMinute.toDouble )
    }
    TimeSeriesCollection(timeSeries)

  def buildPulseRenderer(): XYItemRenderer =
    val renderer = XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = SimpleDateFormat("d,H:m").format( jdate.Date( xValue.toLong ) )
        val beatsPerMinute = DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Context.titlePulse}: ($dayHourMinute, $beatsPerMinute, $delta%)"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer