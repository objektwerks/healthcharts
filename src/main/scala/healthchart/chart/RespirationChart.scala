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

object RespirationChart extends Chart:
  def build(respirations: Entities[Respiration]): JFreeChart =
    val xyPlot = new XYPlot()
    xyPlot.setDataset( buildRespirationDataset(respirations) )
    xyPlot.setRenderer( buildRespirationRenderer() )

    val xAxis = new DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(xAxis)

    val yAxis = new NumberAxis(Context.titleRespirationChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titleRespiration, respirations.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildRespirationDataset(respirations: Entities[Respiration]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleRespiration)
    respirations.entities.foreach { respiration =>
      timeSeries.add( respiration.datetime, respiration.breathesPerMinute.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildRespirationRenderer(): XYItemRenderer =
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val breathesPerMinute = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Context.titleRespiration}: ($dayHourMinute, $breathesPerMinute, $delta%)"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer