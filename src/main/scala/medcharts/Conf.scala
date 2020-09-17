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
  val labelMedCsv = conf.getString("label.medCsv")

  val titleFileChooser = conf.getString("title.fileChooser")
  val titleMenuBar = conf.getString("title.menuBar")
  val titlePathDialog = conf.getString("title.pathDialog")
  val titlePathsDialog = conf.getString("title.pathsDialog")
  val titleGlucoseMed = conf.getString("title.glucoseMed")
  val titleGlucose = conf.getString("title.glucose")
  val titleMed = conf.getString("title.med")
  val titleWeight = conf.getString("title.weight")
  val titleGlucoseMedChartXAxis = conf.getString("title.glucoseMedChartXAxis")
  val titleGlucoseMedChartYAxis = conf.getString("title.glucoseMedChartYAxis")
}