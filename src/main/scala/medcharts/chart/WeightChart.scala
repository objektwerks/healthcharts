package medcharts.chart

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import medcharts.Conf
import medcharts.entity.Converter._
import medcharts.entity._

import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.chart.JFreeChart
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.{IntervalXYDataset, XYDataset}

object WeightChart extends Chart {
  def build(weights: Entities[Weight]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDataset( buildWeightDataset(weights) )
    xyPlot.setRenderer( buildWeightRenderer() )

    val xAxis = new DateAxis(Conf.titleWeightChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(xAxis)

    val yAxis = new NumberAxis(Conf.titleWeightChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(weights.entities)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  private def buildWeightDataset(weights: Entities[Weight]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleWeight)
    weights.entities.foreach { weight =>
      timeSeries.add( weight.datetime, weight.pounds )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildWeightRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val pounds = new DecimalFormat("0").format( yValue )
        val delta = calculateDelta(dataset, series, item)
        s"${Conf.titleWeight}: ($dayHourMinute, $pounds, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildTitle(weights: Array[Weight]): String = {
    if (weights.length >= 2) {
      val first = minuteToYearMonthDay(weights.head.datetime)
      val last = minuteToYearMonthDay(weights.last.datetime)
      s"${Conf.titleWeight} : $first - $last"
    } else Conf.titleWeight
  }
}