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
  val labelGlucoseCsv = conf.getString("label.glucoseCsv")
  val labelMedsCsv = conf.getString("label.medsCsv")

  val titleFileChooser = conf.getString("title.fileChooser")
  val titleMenuBar = conf.getString("title.menuBar")
  val titlePathDialog = conf.getString("title.pathDialog")
  val titlePathsDialog = conf.getString("title.pathsDialog")
  val titleGlucoseMeds = conf.getString("title.glucoseMeds")
  val titleGlucose = conf.getString("title.glucose")
  val titleMeds = conf.getString("title.meds")
  val titleGlucoseMedsChartXAxis = conf.getString("title.glucoseMedsChartXAxis")
  val titleGlucoseMedsChartYAxis = conf.getString("title.glucoseMedsChartYAxis")
  val titleCsvFilesEmpty = conf.getString("title.csvFilesEmpty")
}