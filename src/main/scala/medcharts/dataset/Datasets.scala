package medcharts.dataset

import medcharts.Conf
import medcharts.entity.{BloodPressure, Entities, Glucose, Med}
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

  def buildGlucoseDataset(glucoses: Entities[Glucose]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleGlucose)
    glucoses.entities.foreach { glucose =>
      timeSeries.add( glucose.datetime, glucose.level.toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }

  def buildMedDataset(meds: Entities[Med]): IntervalXYDataset = {
    val timeSeries = new TimeSeries(Conf.titleMed)
    meds.entities.foreach { med =>
      timeSeries.add( med.datetime, s"${med.dosage}.${med.medtype.id}".toDouble )
    }
    new TimeSeriesCollection(timeSeries)
  }
}