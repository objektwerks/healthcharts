package medcharts.chart

import java.awt.event.ActionEvent

import medcharts.Conf
import medcharts.entity._
import medcharts.ui.{Frame, PathDialog}

class VitalsAction(name: String, frame: Frame) extends ChartAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val path = new PathDialog(frame, Conf.labelVitalsCsv).view
    path match {
      case Some( vitalsCsvPath ) =>
        val vitals = transformEntities[Vitals](vitalsCsvPath)
        val chart = VitalsChart.build(vitals)
        val chartPanel = buildChartPanel(chart)
        frame.addChartPanel(s"${Conf.titleVitals}-${counter.getAndIncrement}", chartPanel)
      case None =>
    }
  }
}