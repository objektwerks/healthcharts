package medcharts.chart.glucosemed

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import medcharts.Conf
import medcharts.chart.Chart
import medcharts.entity._

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.{IntervalXYDataset, XYDataset}

import scala.util.Try

object GlucoseMedChart extends Chart {
  def build(glucoses: Entities[Glucose], meds: Entities[Med]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildGlucoseDataset(glucoses))
    xyPlot.setRenderer(0, buildGlucoseRenderer())

    xyPlot.setDataset(1, buildMedDataset(meds))
    xyPlot.setRenderer(1, buildMedRenderer())

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titleGlucoseMedChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Conf.titleGlucoseMed, glucoses.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  def buildGlucoseDataset(glucoses: Entities[Glucose]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleGlucose)
    glucoses.entities.foreach { glucose =>
      timeSeries.add( glucose.datetime, glucose.level.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildMedDataset(meds: Entities[Med]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleMed)
    meds.entities.foreach { med =>
      timeSeries.add( med.datetime, s"${med.dosage}.${med.medtype.id}".toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildGlucoseRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val level = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $level, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  def buildMedRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val yValues = yValue.toString.split("\\.")
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val dosage = Try{ yValues(0).toInt }.getOrElse(-1)
        val medtype = Try{ yValues(1).toInt }.getOrElse(-1)
        val med = MedType.idToMedType.getOrElse(medtype, "n/a")
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $dosage, $med, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }
}