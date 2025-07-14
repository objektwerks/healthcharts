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
    val xyPlot = XYPlot()
    xyPlot.setDataset( buildRespirationDataset(respirations) )
    xyPlot.setRenderer( buildRespirationRenderer() )

    val xAxis = DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( SimpleDateFormat("d,H") )

    xyPlot.setDomainAxis(xAxis)

    val yAxis = NumberAxis(Context.titleRespirationChartYAxis)

    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titleRespiration, respirations.toEntity)
    JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildRespirationDataset(respirations: Entities[Respiration]): XYDataset =
    val timeSeries = TimeSeries(Context.titleRespiration)

    respirations.entities.foreach { respiration =>
      timeSeries.add( respiration.datetime, respiration.breathesPerMinute.toDouble )
    }

    TimeSeriesCollection(timeSeries)

  def buildRespirationRenderer(): XYItemRenderer =
    val renderer = XYLineAndShapeRenderer()

    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = SimpleDateFormat("d,H:m").format( jdate.Date( xValue.toLong ) )
        val breathesPerMinute = DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Context.titleRespiration}: ($dayHourMinute, $breathesPerMinute, $delta%)"
      override def clone() = this
    }
    
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer