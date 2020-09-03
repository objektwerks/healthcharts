package objektwerks.chart

import com.typesafe.config.ConfigFactory

object Conf {
  val conf = ConfigFactory.load("chart.conf")
  val title = conf.getString("title")
  val width = conf.getInt("width")
  val height = conf.getInt("height")
}