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
import org.jfree.data.xy.XYDataset

object PulseChart extends Chart {
  def build(pulses: Entities[Pulse]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDataset( buildPulseDataset(pulses) )
    xyPlot.setRenderer( buildPulseRenderer() )

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(xAxis)

    val yAxis = new NumberAxis(Conf.titlePulseChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Conf.titlePulse, pulses.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  def buildPulseDataset(pulses: Entities[Pulse]): XYDataset = {
    val timeSeries = new TimeSeries(Conf.titlePulse)
    pulses.entities.foreach { pulse =>
      timeSeries.add( pulse.datetime, pulse.beatsPerMinute.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildPulseRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val beatsPerMinute = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Conf.titlePulse}: ($dayHourMinute, $beatsPerMinute, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer
  }
}