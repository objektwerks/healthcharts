package objektwerks.chart

import java.text.DecimalFormat
import java.text.SimpleDateFormat

import org.jfree.chart.ChartPanel
import org.jfree.data.time.TimeSeries
import org.jfree.data.time.TimeSeriesCollection
import org.jfree.chart.renderer.xy.XYBarRenderer
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.axis.DateAxis
import org.jfree.chart.axis.DateTickMarkPosition
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.axis.ValueAxis
import org.jfree.chart.renderer.xy.XYItemRenderer
import org.jfree.chart.renderer.xy.StandardXYItemRenderer
import org.jfree.chart.plot.DatasetRenderingOrder
import org.jfree.chart.JFreeChart

import scala.util.Success
import scala.util.Failure

import Logger._
import Transformer._

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
    val glucoseTimeSeries = buildGlucoseTimeSeries(glucoses)
    val glucoseRenderer = buildGlucoseRenderer()

    val medTimeSeries = buildMedTimeSeries(meds)
    val medRenderer = buildMedRenderer()

    val dateAxis = buildDateAxis()
    val valueAxis = buildValueAxis()

    val xyPlot = new XYPlot(glucoseTimeSeries, dateAxis, valueAxis, glucoseRenderer)
    xyPlot.setDataset(1, medTimeSeries)
    xyPlot.setRenderer(1, medRenderer)
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)
  
    val chart = new JFreeChart("Glucose-Meds Chart", JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true);
    new ChartPanel(chart)
  }

  private def buildGlucoseTimeSeries(glucoses: Glucoses): TimeSeriesCollection = {
    val timeSeries = new TimeSeries("Glucose")
    glucoses.lines.foreach { glucose =>
      timeSeries.add( glucose.datetime, glucose.level.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildMedTimeSeries(meds: Meds): TimeSeriesCollection = {
    val timeSeries = new TimeSeries("Meds")
    meds.lines.foreach { med =>
      timeSeries.add( med.datetime, med.dosage.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  private def buildGlucoseRenderer(): XYItemRenderer = {
    val renderer = new XYBarRenderer(0.20)
    val tooltipGenerator = new StandardXYToolTipGenerator( 
        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
        new SimpleDateFormat("d-MMM-yyyy"),
        new DecimalFormat("0.00")
      )
    renderer.setSeriesToolTipGenerator(0, tooltipGenerator)
    renderer
  }

  private def buildMedRenderer(): XYItemRenderer = {
    val renderer = new StandardXYItemRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator(
      StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
      new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")
    )
    renderer.setSeriesToolTipGenerator(1, tooltipGenerator)
    renderer
  }

  private def buildDateAxis(): DateAxis = {
    val dateAxis = new DateAxis("Date")
    dateAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE)
    dateAxis
  }

  private def buildValueAxis(): ValueAxis = new NumberAxis("Value")
}