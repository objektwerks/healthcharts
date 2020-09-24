package medcharts.action

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.chart.RespirationChart
import medcharts.entity._
import medcharts.ui.{Frame, PathDialog}

class RespirationAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelRespirationCsv).view
    path match {
      case Some( respirationCsvPath ) =>
        val respirations = transformEntities[Respiration](respirationCsvPath)
        val chart = RespirationChart.build(respirations)
        val chartPanel = buildChartPanel(chart)
        frame.addChartPanel(s"${Conf.titleRespiration}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}