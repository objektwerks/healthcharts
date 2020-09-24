package medcharts.chart.vitals

import java.text.SimpleDateFormat

import medcharts.Conf
import medcharts.chart.Chart
import medcharts.chart.bloodpressure.BloodPressureChart
import medcharts.chart.pulse.PulseChart
import medcharts.chart.pulseoxygen.PulseOxygenChart
import medcharts.chart.respiration.RespirationChart
import medcharts.chart.temperature.TemperatureChart
import medcharts.entity.{Entities, Vitals}

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}
import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.IntervalXYDataset

object VitalsChart extends Chart {
  def build(vitals: Entities[Vitals]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildTemperatureDataset(vitals))
    xyPlot.setRenderer(0, TemperatureChart.buildTemperatureRenderer())

    xyPlot.setDataset(1, buildRespirationDataset(vitals))
    xyPlot.setRenderer(1, RespirationChart.buildRespirationRenderer())

    xyPlot.setDataset(2, buildPulseDataset(vitals))
    xyPlot.setRenderer(2, PulseChart.buildPulseRenderer())

    xyPlot.setDataset(3, buildOxygenDataset(vitals))
    xyPlot.setRenderer(3, PulseOxygenChart.buildOxygenRenderer())

    xyPlot.setDataset(4, buildSystolicDataset(vitals))
    xyPlot.setRenderer(4, BloodPressureChart.buildSystolicRenderer())

    xyPlot.setDataset(5, buildDiastolicDataset(vitals))
    xyPlot.setRenderer(5, BloodPressureChart.buildDiastolicRenderer())

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titleVitalsChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Conf.titleVitals, vitals.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }

  def buildTemperatureDataset(vitals: Entities[Vitals]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleTemperature)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.temperature )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildRespirationDataset(vitals: Entities[Vitals]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleRespiration)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.respiration.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildPulseDataset(vitals: Entities[Vitals]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titlePulse)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.pulse.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildOxygenDataset(vitals: Entities[Vitals]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleOxygen)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.oxygen.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildSystolicDataset(vitals: Entities[Vitals]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleSystolic)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.systolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildDiastolicDataset(vitals: Entities[Vitals]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleDiastolic)
    vitals.entities.foreach { vital =>
      timeSeries.add( vital.datetime, vital.diastolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }
}