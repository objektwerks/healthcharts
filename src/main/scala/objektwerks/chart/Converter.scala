package objektwerks.chart

import java.time.LocalDateTime

import org.jfree.data.time.Minute

object Converter {
  def datetimeToMinute(datetime: String): Minute = {
    val localDateTime = LocalDateTime.parse(datetime)
    new Minute(
      localDateTime.getMinute,
      localDateTime.getHour,
      localDateTime.getDayOfMonth,
      localDateTime.getMonthValue,
      localDateTime.getYear
    )
  }
}