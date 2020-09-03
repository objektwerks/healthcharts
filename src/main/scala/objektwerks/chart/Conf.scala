package objektwerks.chart

import com.typesafe.config.ConfigFactory

object Conf {
  val conf = ConfigFactory.load("chart.conf")

  val title = conf.getString("title")
  val width = conf.getInt("width")
  val height = conf.getInt("height")

  val menuBarTitle = conf.getString("menuBarTitle")

  val glucoseMedsTitle = conf.getString("glucoseMedsTitle")
  val medsTitle = conf.getString("medsTitle")

  val glucoseMedsChartXAxisTitle = conf.getString("glucoseMedsChartXAxisTitle")
  val glucoseMedsChartYAxisTitle = conf.getString("glucoseMedsChartYAxisTitle")
  val glucoseMedsDialogTitle = conf.getString("glucoseMedsDialogTitle")
}