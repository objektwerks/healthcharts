package medcharts.chart

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import medcharts.Conf
import medcharts.entity._

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.{IntervalXYDataset, XYDataset}

object TemperatureChart extends Chart {
  def build(temperatures: Entities[Temperature]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDataset( buildTemperatureDataset(temperatures) )
    xyPlot.setRenderer( buildTemperatureRenderer() )

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(xAxis)

    val yAxis = new NumberAxis(Conf.titleTemperatureChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Conf.titleTemperature, temperatures.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  def buildTemperatureDataset(temperatures: Entities[Temperature]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleTemperature)
    temperatures.entities.foreach { weight =>
      timeSeries.add( weight.datetime, weight.degrees )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildTemperatureRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val degrees = new DecimalFormat("0.0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Conf.titleTemperature}: ($dayHourMinute, $degrees, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }
}