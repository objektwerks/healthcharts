package medcharts

import com.typesafe.config.ConfigFactory

import scala.jdk.CollectionConverters._

object Conf {
  val conf = ConfigFactory.load("app.conf")

  val appTitle = conf.getString("app.title")
  val appWidth = conf.getInt("app.width")
  val appHeight = conf.getInt("app.height")

  val fileFilterExtensions = conf.getStringList("file.filterExtensions").asScala.toArray
  val fileExtensionFilterDesc = conf.getString("file.extensionsFilterDesc")

  val labelCancel = conf.getString("label.cancel")
  val labelSelect = conf.getString("label.select")
  val labelEllipsis = conf.getString("label.ellipsis")
  val labelBloodPressureCsv = conf.getString("label.bloodPressureCsv")
  val labelGlucoseCsv = conf.getString("label.glucoseCsv")
  val labelMedCsv = conf.getString("label.medCsv")
  val labelPulseCsv = conf.getString("label.pulseCsv")
  val labelPulseOxygenCsv = conf.getString("label.pulseOxygenCsv")
  val labelRespirationCsv = conf.getString("label.respirationCsv")
  val labelVitalsCsv = conf.getString("label.vitalsCsv")
  val labelTemperatureCsv = conf.getString("label.temperatureCsv")
  val labelWeightCsv = conf.getString("label.weightCsv")

  val titleFileChooser = conf.getString("title.fileChooser")
  val titleMenuBar = conf.getString("title.menuBar")
  val titlePathDialog = conf.getString("title.pathDialog")
  val titlePathsDialog = conf.getString("title.pathsDialog")
  val titleDayHourChartXAxis = conf.getString("title.dayHourChartXAxis")
  val titleBloodPressure = conf.getString("title.bloodPressure")
  val titleBloodPressureChartYAxis = conf.getString("title.bloodPressureChartYAxis")
  val titleSystolic = conf.getString("title.systolic")
  val titleDiastolic = conf.getString("title.diastolic")
  val titleGlucoseMed = conf.getString("title.glucoseMed")
  val titleGlucose = conf.getString("title.glucose")
  val titleMed = conf.getString("title.med")
  val titleGlucoseMedChartYAxis = conf.getString("title.glucoseMedChartYAxis")
  val titlePulse = conf.getString("title.pulse")
  val titleOxygen = conf.getString("title.oxygen")
  val titlePulseChartYAxis = conf.getString("title.pulseChartYAxis")
  val titlePulseOxygen = conf.getString("title.pulseOxygen")
  val titlePulseOxygenChartYAxis = conf.getString("title.pulseOxygenChartYAxis")
  val titleRespiration = conf.getString("title.respiration")
  val titleRespirationChartYAxis = conf.getString("title.respirationChartYAxis")
  val titleTemperature = conf.getString("title.temperature")
  val titleTemperatureChartYAxis = conf.getString("title.temperatureChartYAxis")
  val titleVitals = conf.getString("title.vitals")
  val titleVitalsChartYAxis = conf.getString("title.vitalsChartYAxis")
  val titleWeight = conf.getString("title.weight")
  val titleWeightChartYAxis = conf.getString("title.weightChartYAxis")
}