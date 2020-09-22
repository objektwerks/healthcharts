package medcharts.chart.bloodpressure

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import medcharts.Conf
import medcharts.chart.Chart
import medcharts.entity.Converter.minuteToYearMonthDay
import medcharts.entity.{BloodPressure, Entities}

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.{IntervalXYDataset, XYDataset}

object BloodPressureChart extends Chart {
  def build(bloodpressures: Entities[BloodPressure]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildSystolicDataset(bloodpressures))
    xyPlot.setRenderer(0, buildSystolicRenderer())

    xyPlot.setDataset(1, buildDiastolicDataset(bloodpressures))
    xyPlot.setRenderer(1, buildDiastolicRenderer())

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titleBloodPressureChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(bloodpressures.entities)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  private def buildSystolicDataset(bloodpressures: Entities[BloodPressure]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleSystolic)
    bloodpressures.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.systolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildDiastolicDataset(bloodpressures: Entities[BloodPressure]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleDiastolic)
    bloodpressures.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.diastolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildSystolicRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val systolic = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $systolic, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildDiastolicRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val diastolic = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $diastolic, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildTitle(bloodpressures: Array[BloodPressure]): String = {
    if (bloodpressures.length >= 2) {
      val first = minuteToYearMonthDay(bloodpressures.head.datetime)
      val last = minuteToYearMonthDay(bloodpressures.last.datetime)
      s"${Conf.titleBloodPressure} : $first - $last"
    } else Conf.titleBloodPressure
  }
}