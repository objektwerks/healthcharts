package medcharts.chart

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import medcharts.Conf
import medcharts.entity.{BloodPressure, Entities}

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

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

    val title = buildTitle(Conf.titleBloodPressure, bloodpressures.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  def buildSystolicDataset(bloodpressures: Entities[BloodPressure]): XYDataset = {
    val timeSeries = new TimeSeries(Conf.titleSystolic)
    bloodpressures.entities.foreach { bloodpressure =>
      timeSeries.add( bloodpressure.datetime, bloodpressure.systolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildDiastolicDataset(bloodpressures: Entities[BloodPressure]): XYDataset = {
    val timeSeries = new TimeSeries(Conf.titleDiastolic)
    bloodpressures.entities.foreach { bloodpressure =>
      timeSeries.add( bloodpressure.datetime, bloodpressure.diastolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildSystolicRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val systolic = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Conf.titleSystolic}: ($dayHourMinute, $systolic, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer
  }

  def buildDiastolicRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val diastolic = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Conf.titleDiastolic}: ($dayHourMinute, $diastolic, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer
  }
}