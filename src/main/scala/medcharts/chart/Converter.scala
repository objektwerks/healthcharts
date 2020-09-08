package medcharts.chart

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

  def minuteToYearMonthDay(minute: Minute): String = {
    val hour = minute.getHour
    val year = hour.getYear
    val month = hour.getMonth
    val day = hour.getDayOfMonth
    s"$year.$month.$day"
  }
}