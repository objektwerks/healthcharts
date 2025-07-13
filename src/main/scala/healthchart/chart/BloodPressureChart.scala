package healthchart.chart

import java.text.SimpleDateFormat
import java.{util => jdate}

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.{StandardXYItemLabelGenerator, StandardXYToolTipGenerator}
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

import healthchart.Context
import healthchart.entity.{BloodPressure, Entities}

object BloodPressureChart extends Chart:
  def build(bloodpressures: Entities[BloodPressure]): JFreeChart =
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildBloodPressureDataset(bloodpressures))
    xyPlot.setRenderer(0, buildBloodPressureRenderer())

    val xAxis = new DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Context.titleBloodPressureChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titleBloodPressure, bloodpressures.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildBloodPressureDataset(bloodpressures: Entities[BloodPressure]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleSystolic)
    bloodpressures.entities.foreach { bloodpressure =>
      timeSeries.add( bloodpressure.datetime, s"${bloodpressure.systolic}.${bloodpressure.diastolic }".toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildBloodPressureRenderer(): XYItemRenderer =
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val yValues = yValue.toString.split("\\.")
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val systolic = yValues(0)
        var diastolic = yValues(1)
        if (diastolic.length == 1) diastolic = diastolic + 0  // Hack! DecimalFormat dropping trailing zero!
        val bloodpressure = s"$systolic/$diastolic"
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Context.titleBloodPressure}: ($dayHourMinute, $bloodpressure, $delta%)"
      override def clone() = this
    }
    
    val itemLabelGenerator = new StandardXYItemLabelGenerator() {
      override def generateLabel(dataset: XYDataset, series: Int, item: Int): String =
        val yValue = dataset.getYValue(series, item)
        val yValues = yValue.toString.split("\\.")
        val systolic = yValues(0)
        var diastolic = yValues(1)
        if (diastolic.length == 1) diastolic = diastolic + 0  // Hack! DecimalFormat dropping trailing zero!
        s"$systolic/$diastolic"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( itemLabelGenerator )
    renderer.setDefaultItemLabelsVisible(true)
    renderer