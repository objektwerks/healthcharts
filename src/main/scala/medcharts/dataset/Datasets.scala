package medcharts.dataset

import medcharts.Conf
import medcharts.entity.{BloodPressure, Entities}

import org.jfree.data.time.{TimeSeries, TimeSeriesCollection}
import org.jfree.data.xy.IntervalXYDataset

object Datasets {
  def buildSystolicDataset(bloodpressures: Entities[BloodPressure]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleSystolic)
    bloodpressures.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.systolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildDiastolicDataset(bloodpressures: Entities[BloodPressure]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleDiastolic)
    bloodpressures.entities.foreach { pulseoxygen =>
      timeSeries.add( pulseoxygen.datetime, pulseoxygen.diastolic.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

}