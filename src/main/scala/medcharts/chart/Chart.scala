package medcharts.chart

import medcharts.entity.Converter.minuteToYearMonthDay
import medcharts.entity.Entity

import org.jfree.data.xy.XYDataset

import scala.math.abs

abstract class Chart {
  def buildTitle(title: String, entities: Array[Entity]): String = {
    if (entities.length >= 2) {
      val first = minuteToYearMonthDay(entities.head.datetime)
      val last = minuteToYearMonthDay(entities.last.datetime)
      s"$title : $first - $last"
    } else title
  }

  def calculateDeltaAsPercentage(dataset: XYDataset, series: Int, item: Int): Long = {
    val datasetLastIndex = dataset.getItemCount(series) - 1
    val previousItemIndex = item - 1
    val datasetIndexRange = Range.inclusive(0, datasetLastIndex)
    if ( datasetIndexRange.contains( previousItemIndex ) ) {
      val yCurrentValue = dataset.getYValue(series, item)
      val yPreviousValue = dataset.getYValue(series, previousItemIndex)
      val yDividendValue = yCurrentValue - yPreviousValue
      val yDivisorValue = ( yCurrentValue + yPreviousValue ) / 2
      val yValueDelta = abs( yDividendValue / yDivisorValue ) * 100
      yValueDelta.round
    } else 0
  }
}