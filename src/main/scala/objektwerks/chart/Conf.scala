package objektwerks.chart

import com.typesafe.config.ConfigFactory

object Conf {
  val conf = ConfigFactory.load("chart.conf")
}