package healthchart.chart

import java.text.{DecimalFormat, SimpleDateFormat}
import java.{util => jdate}

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.labels.StandardXYToolTipGenerator
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.chart.renderer.xy.{XYItemRenderer, XYLineAndShapeRenderer}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

import healthchart.Context
import healthchart.chart.PulseChart._
import healthchart.entity.{Entities, PulseOxygen}

object PulseOxygenChart extends Chart:
  def build(pulseoxygens: Entities[PulseOxygen]): JFreeChart =
    val xyPlot = XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)
    xyPlot.setDataset(0, buildPulseDataset(pulseoxygens))
    xyPlot.setRenderer(0, buildPulseRenderer())
    xyPlot.setDataset(1, buildOxygenDataset(pulseoxygens))
    xyPlot.setRenderer(1, buildOxygenRenderer())

    val xAxis = DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( SimpleDateFormat("d,H") )

    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = NumberAxis(Context.titlePulseOxygenChartYAxis)

    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Context.titlePulseOxygen, pulseoxygens.toEntity)
    JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)

  def buildPulseDataset(pulseoxygens: Entities[PulseOxygen]): XYDataset =
    val timeSeries = TimeSeries(Context.titlePulse)

    pulseoxygens.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.beatsPerMinute.toDouble )
    }

    TimeSeriesCollection(timeSeries)

  def buildOxygenDataset(pulseoxygens: Entities[PulseOxygen]): XYDataset =
    val timeSeries = TimeSeries(Context.titleOxygen)

    pulseoxygens.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.bloodOxygenPercentage.toDouble )
    }

    TimeSeriesCollection(timeSeries)

  def buildOxygenRenderer(): XYItemRenderer =
    val renderer = XYLineAndShapeRenderer()

    val tooltipGenerator = new StandardXYToolTipGenerator() {
      override def generateToolTip(dataset: XYDataset, series: Int, item: Int): String =
        val xValue = dataset.getXValue(series, item)
        val yValue = dataset.getYValue(series, item)
        val dayHourMinute = SimpleDateFormat("d,H:m").format( jdate.Date( xValue.toLong ) )
        val bloodOxygenPercentage = DecimalFormat("0").format( yValue )
        val delta = calculateDeltaAsPercentage(dataset, series, item)
        s"${Context.titleOxygen}: ($dayHourMinute, $bloodOxygenPercentage, $delta%)"
      override def clone() = this
    }
    
    renderer.setDefaultToolTipGenerator(tooltipGenerator)
    renderer.setDefaultShapesVisible(true)
    renderer.setDefaultItemLabelGenerator( buildItemLabelGenerator("0") )
    renderer.setDefaultItemLabelsVisible(true)
    renderer