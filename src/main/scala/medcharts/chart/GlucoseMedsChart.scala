package medcharts.chart

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import javax.swing.BorderFactory

import medcharts.Conf
import medcharts.entity.Converter._
import medcharts.entity._

import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.chart.{ChartPanel, JFreeChart}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.{IntervalXYDataset, XYDataset}

import scala.util.Try

object GlucoseMedsChart extends Chart {
  def build(glucoses: Entities[Glucose], meds: Entities[Med]): ChartPanel = {
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildGlucoseDataset(glucoses))
    xyPlot.setRenderer(0, buildGlucoseRenderer())

    xyPlot.setDataset(1, buildMedDataset(meds))
    xyPlot.setRenderer(1, buildMedRenderer())

    val xAxis = new DateAxis(Conf.titleGlucoseMedsChartXAxis)
    xAxis.setDateFormatOverride(new SimpleDateFormat("d,H"))
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titleGlucoseMedsChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(glucoses.entities)
    val chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

    val chartPanel = new ChartPanel(chart)
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15))
    chartPanel.setInitialDelay(100)
    chartPanel.setReshowDelay(100)
    chartPanel.setDismissDelay(10000)
    chartPanel
  }

  private def buildGlucoseDataset(glucoses: Entities[Glucose]): IntervalXYDataset = {
    val timeSeries = new TimeSeries("Glucose".asInstanceOf[Comparable[String]])
    glucoses.entities.foreach { glucose =>
      timeSeries.add( glucose.datetime, glucose.level.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildMedDataset(meds: Entities[Med]): IntervalXYDataset = {
    val timeSeries = new TimeSeries("Meds".asInstanceOf[Comparable[String]])
    meds.entities.foreach { med =>
      timeSeries.add( med.datetime, s"${med.dosage}.${med.medtype.id}".toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildGlucoseRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val level = new DecimalFormat("0").format( yValue )
        val delta = calculateDelta(dataset, series, item)
        s"${Conf.titleGlucose}: ($dayHourMinute, $level, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildMedRenderer(): XYItemRenderer = {
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
        val delta = calculateDelta(dataset, series, item)
        s"${Conf.titleMeds}: ($dayHourMinute, $dosage, $med, $delta%)"
      }
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildTitle(glucoses: Array[Glucose]): String = {
    if (glucoses.length >= 2) {
      val first = minuteToYearMonthDay(glucoses.head.datetime)
      val last = minuteToYearMonthDay(glucoses.last.datetime)
      s"${Conf.titleGlucoseMeds} : $first - $last"
    } else Conf.titleGlucoseMeds
  }
}