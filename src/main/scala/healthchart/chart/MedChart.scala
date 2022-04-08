package healthchart.chart

import java.text.SimpleDateFormat
import java.{util => jdate}

import healthchart.Conf
import healthchart.entity._

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

import scala.util.Try

object MedChart extends Chart:
  def build(meds: Entities[Med]): JFreeChart =
    val xyPlot = new XYPlot()

    xyPlot.setDataset(1, buildMedDataset(meds))
    xyPlot.setRenderer(1, buildMedRenderer())

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titleGlucoseMedChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Conf.titleMed, meds.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildMedDataset(meds: Entities[Med]): XYDataset =
    val timeSeries = new TimeSeries(Conf.titleMed)
    meds.entities.foreach { med =>
      timeSeries.add( med.datetime, s"${med.dosage}.${med.medtype.id}".toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildMedRenderer(): XYItemRenderer =
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val yValues = yValue.toString.split("\\.")
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val dosage = Try{ yValues(0).toInt }.getOrElse(-1)
        val medtype = Try{ yValues(1).toInt }.getOrElse(-1)
        val med = MedType.idToMedType.getOrElse(medtype, "n/a")
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Conf.titleMed}: ($dayHourMinute, $dosage, $med, $delta%)"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer