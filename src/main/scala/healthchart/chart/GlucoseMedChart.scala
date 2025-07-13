package healthchart.chart

import java.text.SimpleDateFormat

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

import healthchart.Context
import healthchart.entity.*

object GlucoseMedChart extends Chart:
  def build(glucosemeds: Entities[GlucoseMed]): JFreeChart =
    val xyPlot = XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildGlucoseDataset(glucosemeds))
    xyPlot.setRenderer(0, GlucoseChart.buildGlucoseRenderer())

    xyPlot.setDataset(1, buildMedDataset(glucosemeds))
    xyPlot.setRenderer(1, MedChart.buildMedRenderer())

    val xAxis = DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = NumberAxis(Context.titleGlucoseMedChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titleGlucoseMed, glucosemeds.toEntity)
    JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildGlucoseDataset(glucosemeds: Entities[GlucoseMed]): XYDataset =
    val timeSeries = TimeSeries(Context.titleGlucose)
    glucosemeds.entities.foreach { glucosemed =>
      timeSeries.add( glucosemed.datetime, glucosemed.level.toDouble )
    }
    TimeSeriesCollection(timeSeries)

  def buildMedDataset(glucosemeds: Entities[GlucoseMed]): XYDataset =
    val timeSeries = TimeSeries(Context.titleMed)
    glucosemeds.entities.foreach { glucosemed =>
      timeSeries.add( glucosemed.datetime, s"${glucosemed.dosage}.${glucosemed.medtype.id}".toDouble )
    }
    TimeSeriesCollection(timeSeries)