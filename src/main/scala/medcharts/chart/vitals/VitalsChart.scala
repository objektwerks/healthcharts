package medcharts.chart.vitals

import java.text.SimpleDateFormat

import medcharts.Conf
import medcharts.chart.Chart
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
}