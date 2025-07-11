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

object GlucoseChart extends Chart:
  def build(glucoses: Entities[Glucose]): JFreeChart =
    val xyPlot = new XYPlot()

    xyPlot.setDataset(0, buildGlucoseDataset(glucoses))
    xyPlot.setRenderer(0, buildGlucoseRenderer())

    val xAxis = new DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Context.titleGlucoseMedChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titleGlucose, glucoses.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildGlucoseDataset(glucoses: Entities[Glucose]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleGlucose)
    glucoses.entities.foreach { glucose =>
      timeSeries.add( glucose.datetime, glucose.level.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildGlucoseRenderer(): XYItemRenderer =
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val level = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Context.titleGlucose}: ($dayHourMinute, $level, $delta%)"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer