package healthchart

import com.typesafe.config.ConfigFactory

import scala.jdk.CollectionConverters.*

object Context:
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
  val labelCaloriesWeightCsv = conf.getString("label.caloriesWeightCsv")
  val labelGlucoseCsv = conf.getString("label.glucoseCsv")
  val labelMedCsv = conf.getString("label.medCsv")
  val labelGlucoseMedCsv = conf.getString("label.glucoseMedCsv")
  val labelPulseCsv = conf.getString("label.pulseCsv")
  val labelPulseOxygenCsv = conf.getString("label.pulseOxygenCsv")
  val labelRespirationCsv = conf.getString("label.respirationCsv")
  val labelVitalsCsv = conf.getString("label.vitalsCsv")
  val labelTemperatureCsv = conf.getString("label.temperatureCsv")
  val labelWeightCsv = conf.getString("label.weightCsv")

  val tabChart = conf.getString("tab.chart")
  val tabEntities = conf.getString("tab.entities")
  val tabInvalidEntities = conf.getString("tab.invalidEntities")

  val titleFileChooser = conf.getString("title.fileChooser")
  val titleMenuBar = conf.getString("title.menuBar")
  val titlePathDialog = conf.getString("title.pathDialog")
  val titlePathsDialog = conf.getString("title.pathsDialog")
  val titleDayHourChartXAxis = conf.getString("title.dayHourChartXAxis")
  val titleBloodPressure = conf.getString("title.bloodPressure")
  val titleBloodPressureChartYAxis = conf.getString("title.bloodPressureChartYAxis")
  val titleSystolic = conf.getString("title.systolic")
  val titleDiastolic = conf.getString("title.diastolic")
  val titleCaloriesWeight = conf.getString("title.caloriesWeight")
  val titleCaloriesIn = conf.getString("title.caloriesIn")
  val titleCaloriesOut = conf.getString("title.caloriesOut")
  val titleCaloriesWeightChartTopYAxis = conf.getString("title.caloriesWeightChartTopYAxis")
  val titleCaloriesWeightChartBottomYAxis = conf.getString("title.caloriesWeightChartBottomYAxis")
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
  val titleVitalsChartTopYAxis = conf.getString("title.vitalsChartTopYAxis")
  val titleVitalsChartBottomYAxis = conf.getString("title.vitalsChartBottomYAxis")
  val titleWeight = conf.getString("title.weight")
  val titleWeightChartYAxis = conf.getString("title.weightChartYAxis")