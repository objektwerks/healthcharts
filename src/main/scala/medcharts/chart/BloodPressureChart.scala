package medcharts.chart

import java.text.SimpleDateFormat

import medcharts.Conf
import medcharts.dataset.Datasets._
import medcharts.entity.{BloodPressure, Entities}
import medcharts.renderer.Renderers._

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.{DateAxis, NumberAxis}
import org.jfree.chart.plot.{DatasetRenderingOrder, XYPlot}

object BloodPressureChart extends Chart {
  def build(bloodpressures: Entities[BloodPressure]): JFreeChart = {
    val xyPlot = new XYPlot()
    xyPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD)

    xyPlot.setDataset(0, buildSystolicDataset(bloodpressures))
    xyPlot.setRenderer(0, buildSystolicRenderer())

    xyPlot.setDataset(1, buildDiastolicDataset(bloodpressures))
    xyPlot.setRenderer(1, buildDiastolicRenderer())

    val xAxis = new DateAxis(Conf.titleDayHourChartXAxis)
    xAxis.setDateFormatOverride( new SimpleDateFormat("d,H") )
    xyPlot.setDomainAxis(0, xAxis)

    val yAxis = new NumberAxis(Conf.titleBloodPressureChartYAxis)
    xyPlot.setRangeAxis(yAxis)

    val title = buildTitle(Conf.titleBloodPressure, bloodpressures.toEntity)
    new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, xyPlot, true)
  }
}