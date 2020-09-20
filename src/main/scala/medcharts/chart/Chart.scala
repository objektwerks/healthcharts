package medcharts.chart

import java.text.DecimalFormat

import org.jfree.data.xy.XYDataset

import scala.math.abs

abstract class Chart {
  def calculateDeltaAsPercentage(dataset: XYDataset, series: Int, item: Int): String = {
    val datasetLastIndex = dataset.getItemCount(series) - 1
    val previousItemIndex = item - 1
    val datasetIndexRange = Range.inclusive(0, datasetLastIndex)
    if ( datasetIndexRange.contains( previousItemIndex ) ) {
      val yCurrentValue = dataset.getYValue(series, item)
      val yPreviousValue = dataset.getYValue(series, previousItemIndex)
      val yDividendValue = yCurrentValue - yPreviousValue
      val yDivisorValue = ( yCurrentValue + yPreviousValue ) / 2
      val yValueDelta = abs( yDividendValue / yDivisorValue ) * 100
      val formatter = new DecimalFormat("0.0")
      val current = formatter.format(yCurrentValue)
      val previous = formatter.format(yPreviousValue)
      val delta = formatter.format(yValueDelta)
      s"[$current - $previous = $delta%]"
    } else "0%"
  }
}