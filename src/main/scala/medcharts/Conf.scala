package medcharts

import com.typesafe.config.ConfigFactory

object Conf {
  val conf = ConfigFactory.load("chart.conf")

  val title = conf.getString("title")
  val width = conf.getInt("width")
  val height = conf.getInt("height")

  val menuBarTitle = conf.getString("menuBarTitle")
  val glucoseMedsTitle = conf.getString("glucoseMedsTitle")
  val medsTitle = conf.getString("medsTitle")

  val cancelLabel = conf.getString("cancelLabel")
  val selectLabel = conf.getString("selectLabel")
  val ellipsisLabel = conf.getString("ellipsisLabel")
  
  val glucoseMedsChartXAxisTitle = conf.getString("glucoseMedsChartXAxisTitle")
  val glucoseMedsChartYAxisTitle = conf.getString("glucoseMedsChartYAxisTitle")
  val glucoseMedsDialogTitle = conf.getString("glucoseMedsDialogTitle")

  val glucoseCsvLabel = conf.getString("glucoseCsvLabel")
  val medsCsvLabel = conf.getString("medsCsvLabel")
  val glucoseMedsFileChooserTitle = conf.getString("glucoseMedsFileChooserTitle")
  val glucoseMedsFileExtensionFilter = conf.getString("glucoseMedsFileExtensionFilter")
}