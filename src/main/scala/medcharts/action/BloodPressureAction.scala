package medcharts.action

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.BloodPressureChart
import medcharts.entity._
import medcharts.entity.Transformer._
import medcharts.ui.{Frame, PathDialog}

class BloodPressureAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelBloodPressureCsv).view
    path match {
      case Some( bloodPressureCsvPath ) =>
        val bloodpressures = transformEntities[BloodPressure](bloodPressureCsvPath)
        val chart = BloodPressureChart.build(bloodpressures)
        val chartPanel = buildChartPanel(chart)
        frame.addChartPanel(s"${Conf.titleBloodPressure}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}