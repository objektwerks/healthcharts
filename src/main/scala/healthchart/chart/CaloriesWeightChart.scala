package healthchart.chart

import java.awt.Color
import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{CombinedDomainXYPlot, PlotOrientation, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

import healthchart.Context
import healthchart.chart.WeightChart.*
import healthchart.entity.{CaloriesWeight, Entities}

object CaloriesWeightChart extends Chart:
  def build(caloriesWeights: Entities[CaloriesWeight]): JFreeChart =
    val xAxis = new DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )

    val topXYPlot = buildTopXYPlot(caloriesWeights)
    val bottomXYPlot = buildBottomXYPlot(caloriesWeights)
    val combinedXYPlot = buildCombindedXYPlot(xAxis, topXYPlot, bottomXYPlot)

    val title = buildTitle(Context.titleCaloriesWeight, caloriesWeights.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, combinedXYPlot, true)

  def buildCombindedXYPlot(xAxis: DateAxis,
                           topXYPlot: XYPlot, 
                           bottomXYPlot: XYPlot): CombinedDomainXYPlot =
    val combinedXYPlot = new CombinedDomainXYPlot(xAxis)
    combinedXYPlot.setGap(3.0)
    combinedXYPlot.add(topXYPlot, 1)
    combinedXYPlot.add(bottomXYPlot, 1)
    combinedXYPlot.setOrientation(PlotOrientation.VERTICAL)
    combinedXYPlot

  def buildTopXYPlot(caloriesWeights: Entities[CaloriesWeight]): XYPlot =
    val xyPlot = new XYPlot()
    xyPlot.setBackgroundPaint(Color.LIGHT_GRAY)

    val yAxis = new NumberAxis(Context.titleCaloriesWeightChartBottomYAxis)
    yAxis.setAutoRangeIncludesZero(false)
    yAxis.setAutoRange(true)

    xyPlot.setRangeAxis(yAxis)
    xyPlot.getRangeAxis.setUpperMargin(xyPlot.getRangeAxis.getUpperMargin + 0.10)

    xyPlot.setDataset(0, buildCaloriesInDataset(caloriesWeights))
    xyPlot.setRenderer(0, buildCaloriesRenderer())

    xyPlot.setDataset(1, buildCaloriesOutDataset(caloriesWeights))
    xyPlot.setRenderer(1, buildCaloriesRenderer())
    xyPlot

  def buildBottomXYPlot(caloriesWeights: Entities[CaloriesWeight]): XYPlot =
    val xyPlot = new XYPlot()
    xyPlot.setBackgroundPaint(Color.LIGHT_GRAY)

    val yAxis = new NumberAxis(Context.titleCaloriesWeightChartTopYAxis)
    yAxis.setAutoRangeIncludesZero(false)
    yAxis.setAutoRange(true)

    xyPlot.setRangeAxis(yAxis)
    xyPlot.getRangeAxis.setUpperMargin(xyPlot.getRangeAxis.getUpperMargin + 0.10)

    xyPlot.setDataset(0, buildWeightDataset(caloriesWeights))
    xyPlot.setRenderer(0, buildWeightRenderer())
    xyPlot

  def buildWeightDataset(caloriesWeights: Entities[CaloriesWeight]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleWeight)
    caloriesWeights.entities.foreach { caloriesWeight =>
      timeSeries.add( caloriesWeight.datetime, caloriesWeight.weight )
    }
    new TimeSeriesCollection(timeSeries)

  def buildCaloriesInDataset(caloriesWeights: Entities[CaloriesWeight]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleCaloriesIn)
    caloriesWeights.entities.foreach { caloriesWeight =>
      timeSeries.add( caloriesWeight.datetime, caloriesWeight.in.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildCaloriesOutDataset(caloriesWeights: Entities[CaloriesWeight]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleCaloriesOut)
    caloriesWeights.entities.foreach { caloriesWeight =>
      timeSeries.add( caloriesWeight.datetime, caloriesWeight.out.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildCaloriesRenderer(): XYItemRenderer =
    val renderer = new XYLineAndShapeRenderer()
    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = new SimpleDateFormat("d,H:m").format( new jdate.Date( xValue.toLong ) )
        val calorie = new DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"($dayHourMinute, $calorie, $delta%)"
      override def clone() = this
    }
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer