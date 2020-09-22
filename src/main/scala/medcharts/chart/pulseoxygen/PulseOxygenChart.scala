package medcharts.chart.pulseoxygen

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import medcharts.Conf
import medcharts.chart.Chart
import medcharts.entity.Converter.minuteToYearMonthDay
import medcharts.entity.{Entities, PulseOxygen}
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.{IntervalXYDataset, XYDataset}

object PulseOxygenChart extends Chart {
  def build(pulseoxygens: Entities[PulseOxygen]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildPulseDataset(pulseoxygens))
    xyPlot.setRenderer(0, buildPulseRenderer())

    xyPlot.setDataset(1, buildOxygenDataset(pulseoxygens))
    xyPlot.setRenderer(1, buildOxygenRenderer())

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titlePulseOxygenChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(pulseoxygens.entities)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  private def buildPulseDataset(pulseoxygens: Entities[PulseOxygen]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titlePulse)
    pulseoxygens.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.beatsPerMinute.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildOxygenDataset(pulseoxygens: Entities[PulseOxygen]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleOxygen)
    pulseoxygens.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.bloodOxygenPercentage.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildPulseRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val beatsPerMinute = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $beatsPerMinute, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildOxygenRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val bloodOxygenPercentage = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $bloodOxygenPercentage, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildTitle(pulseoxygens: Array[PulseOxygen]): String = {
    if (pulseoxygens.length >= 2) {
      val first = minuteToYearMonthDay(pulseoxygens.head.datetime)
      val last = minuteToYearMonthDay(pulseoxygens.last.datetime)
      s"${Conf.titlePulseOxygen} : $first - $last"
    } else Conf.titlePulseOxygen
  }
}