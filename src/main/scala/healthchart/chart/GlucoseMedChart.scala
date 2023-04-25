package healthchart.chart

import java.text.SimpleDateFormat

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

import healthchart.Conf
import healthchart.entity.*

object GlucoseMedChart extends Chart:
  def build(glucosemeds: Entities[GlucoseMed]): JFreeChart =
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildGlucoseDataset(glucosemeds))
    xyPlot.setRenderer(0, GlucoseChart.buildGlucoseRenderer())

    xyPlot.setDataset(1, buildMedDataset(glucosemeds))
    xyPlot.setRenderer(1, MedChart.buildMedRenderer())

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titleGlucoseMedChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Conf.titleGlucoseMed, glucosemeds.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildGlucoseDataset(glucosemeds: Entities[GlucoseMed]): XYDataset =
    val timeSeries = new TimeSeries(Conf.titleGlucose)
    glucosemeds.entities.foreach { glucosemed =>
      timeSeries.add( glucosemed.datetime, glucosemed.level.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildMedDataset(glucosemeds: Entities[GlucoseMed]): XYDataset =
    val timeSeries = new TimeSeries(Conf.titleMed)
    glucosemeds.entities.foreach { glucosemed =>
      timeSeries.add( glucosemed.datetime, s"${glucosemed.dosage}.${glucosemed.medtype.id}".toDouble )
    }
    new TimeSeriesCollection(timeSeries)