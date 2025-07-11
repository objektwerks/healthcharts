package healthchart.chart

import java.awt.Color
import java.text.SimpleDateFormat

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.plot.{CombinedDomainXYPlot, PlotOrientation, XYPlot}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.XYDataset

import healthchart.Context
import healthchart.chart.BloodPressureChart.*
import healthchart.chart.PulseChart.*
import healthchart.chart.PulseOxygenChart.*
import healthchart.chart.RespirationChart.*
import healthchart.chart.TemperatureChart.*
import healthchart.entity.{Entities, Vitals}

object VitalsChart extends Chart:
  def build(vitals: Entities[Vitals]): JFreeChart =
    val xAxis = new DateAxis(Context.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )

    val topXYPlot = buildTopXYPlot(vitals)
    val bottomXYPlot = buildBottomXYPlot(vitals)
    val combinedXYPlot = buildCombindedXYPlot(xAxis, topXYPlot, bottomXYPlot)

    val title = buildTitle(Context.titleVitals, vitals.toEntity)
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

  def buildTopXYPlot(vitals: Entities[Vitals]): XYPlot =
    val xyPlot = new XYPlot()
    xyPlot.setBackgroundPaint(Color.LIGHT_GRAY)

    val yAxis = new NumberAxis(Context.titleVitalsChartTopYAxis)
    yAxis.setAutoRangeIncludesZero(false)
    yAxis.setAutoRange(true)

    xyPlot.setRangeAxis(yAxis)
    xyPlot.getRangeAxis.setUpperMargin(xyPlot.getRangeAxis.getUpperMargin + 0.10)

    xyPlot.setDataset(0, buildBloodPressureDataset(vitals))
    xyPlot.setRenderer(0, buildBloodPressureRenderer())
    
    xyPlot.setDataset(1, buildTemperatureDataset(vitals))
    xyPlot.setRenderer(1, buildTemperatureRenderer())
    xyPlot

  def buildBottomXYPlot(vitals: Entities[Vitals]): XYPlot =
    val xyPlot = new XYPlot()
    xyPlot.setBackgroundPaint(Color.LIGHT_GRAY)

    val yAxis = new NumberAxis(Context.titleVitalsChartBottomYAxis)
    yAxis.setAutoRangeIncludesZero(false)
    yAxis.setAutoRange(true)

    xyPlot.setRangeAxis(yAxis)
    xyPlot.getRangeAxis.setUpperMargin(xyPlot.getRangeAxis.getUpperMargin + 0.10)

    xyPlot.setDataset(0, buildRespirationDataset(vitals))
    xyPlot.setRenderer(0, buildRespirationRenderer())

    xyPlot.setDataset(1, buildPulseDataset(vitals))
    xyPlot.setRenderer(1, buildPulseRenderer())

    xyPlot.setDataset(2, buildOxygenDataset(vitals))
    xyPlot.setRenderer(2, buildOxygenRenderer())
    xyPlot

  def buildTemperatureDataset(vitals: Entities[Vitals]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleTemperature)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.temperature )
    }
    new TimeSeriesCollection(timeSeries)

  def buildRespirationDataset(vitals: Entities[Vitals]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleRespiration)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.respiration.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildPulseDataset(vitals: Entities[Vitals]): XYDataset =
    val timeSeries = new TimeSeries(Context.titlePulse)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.pulse.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildOxygenDataset(vitals: Entities[Vitals]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleOxygen)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.oxygen.toDouble )
    }
    new TimeSeriesCollection(timeSeries)

  def buildBloodPressureDataset(vitals: Entities[Vitals]): XYDataset =
    val timeSeries = new TimeSeries(Context.titleSystolic)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, s"${vital.systolic}.${vital.diastolic}".toDouble )
    }
    new TimeSeriesCollection(timeSeries)