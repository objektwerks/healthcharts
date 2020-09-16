package medcharts.chart

import org.jfree.data.xy.XYDataset

import scala.math.abs

abstract class Chart {
  def calculateDelta(dataset: XYDataset, series: Int, item: Int): Int = {
    val datasetLastIndex = dataset.getSeriesCount - 1
    val previousItemIndex = item - 1
    val datasetIndexRange = Range.inclusive(0, datasetLastIndex)
    if ( datasetIndexRange.contains( previousItemIndex ) ) {
      val yCurrentValue = dataset.getYValue(series, item)
      val yPreviousValue = dataset.getYValue(series, previousItemIndex)
      val yTopValue = yCurrentValue - yPreviousValue
      val yBottomValue = ( yCurrentValue + yPreviousValue ) / 2
      val delta = abs( yTopValue / yBottomValue ) * 100
      delta.round.toInt
    } else 0
  }
}