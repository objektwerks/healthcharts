package medcharts.ui

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import javax.swing.BorderFactory

import medcharts.Conf
import medcharts.domain.Converter._
import medcharts.domain.Logger._
import medcharts.domain.Transformer._
import medcharts.domain._

import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.chart.{ChartPanel, JFreeChart}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.{IntervalXYDataset, XYDataset}

import scala.util.{Failure, Success, Try}

object GlucoseMedsChart {
  def apply(glucoseCsvPath: String, medsCsvPath: String): ChartPanel = {
    val glucoses = transformGlucoses(glucoseCsvPath)
    val meds = transformMeds(medsCsvPath)
    build(glucoses, meds)
  }

  private def transformGlucoses(path: String): Glucoses =
    transform[Glucoses](path) match {
      case Success(glucoses) => glucoses
      case Failure(failure) =>
        logIOFailure(failure, path)
        Glucoses.empty
    }

  private def transformMeds(path: String): Meds =
    transform[Meds](path) match {
      case Success(meds) => meds
      case Failure(failure) =>
        logIOFailure(failure, path)
        Meds.empty
    }

  private def build(glucoses: Glucoses, meds: Meds): ChartPanel = {
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildGlucoseDataset(glucoses))
    xyPlot.setRenderer(0, buildGlucoseRenderer())

    xyPlot.setDataset(1, buildMedDataset(meds))
    xyPlot.setRenderer(1, buildMedRenderer())

    val domainAxis = new DateAxis(Conf.glucoseMedsChartXAxisTitle)
    domainAxis.setDateFormatOverride(new SimpleDateFormat("d,H"))
    xyPlot.setDomainAxis(0, domainAxis)

    xyPlot.setRangeAxis(new NumberAxis(Conf.glucoseMedsChartYAxisTitle))

    val title = buildTitle(glucoses.lines)
    val chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

    val chartPanel = new ChartPanel(chart)
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15))
    chartPanel.setInitialDelay(100)
    chartPanel.setReshowDelay(100)
    chartPanel
  }

  private def buildGlucoseDataset(glucoses: Glucoses): IntervalXYDataset = {
    val timeSeries = new TimeSeries("Glucose".asInstanceOf[Comparable[String]])
    glucoses.lines.foreach { glucose =>
      timeSeries.add( glucose.datetime, glucose.level.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildMedDataset(meds: Meds): IntervalXYDataset = {
    val timeSeries = new TimeSeries("Meds".asInstanceOf[Comparable[String]])
    meds.lines.foreach { med =>
      timeSeries.add( med.datetime, s"${med.dosage}.${med.medtype.id}".toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildGlucoseRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator( 
        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
        new SimpleDateFormat("d,H:m"),
        new DecimalFormat("0")
      )
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer
  }

  private def buildMedRenderer(): XYItemRenderer = {
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String = {
        val formatter = new SimpleDateFormat("d,H:m")
        val time = formatter.format( new jdate.Date(dataset.getXValue(series, item).toLong) )
        val values = dataset.getYValue(series, item).toString.split("\\.")
        val dosage = Try{ values(0).toInt }.getOrElse(-1)
        val medtype = Try{ values(1).toInt }.getOrElse(-1)
        val med = MedType.idToMedType.getOrElse(medtype, "n/a")
        s"${Conf.medsTitle}: ($time, $dosage, $med)"
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
      s"${Conf.glucoseMedsTitle} : $first - $last"
    } else Conf.glucoseMedsTitle
  }
}