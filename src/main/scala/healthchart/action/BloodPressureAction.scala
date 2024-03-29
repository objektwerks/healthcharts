package healthchart.action

import java.awt.event.ActionEvent
import java.util.concurrent.atomic.AtomicInteger
import javax.swing.AbstractAction

import healthchart.Conf
import healthchart.chart.BloodPressureChart
import healthchart.entity.*
import healthchart.entity.Transformer.*
import healthchart.panel.ChartPanelBuilder
import healthchart.ui.{Frame, PathDialog}

class BloodPressureAction(name: String, frame: Frame) extends AbstractAction(name):
  protected val counter = new AtomicInteger(1)

  def actionPerformed(event: ActionEvent): Unit =
    val path = new PathDialog(frame, Conf.labelBloodPressureCsv).view
    path match
      case Some( bloodPressureCsvPath ) =>
        val bloodpressures = transformEntities[BloodPressure](bloodPressureCsvPath)
        val chart = BloodPressureChart.build(bloodpressures)
        val chartPanel = ChartPanelBuilder.build(chart, bloodpressures)
        frame.addCompositeChartPanel(s"${Conf.titleBloodPressure}-${counter.getAndIncrement}", chartPanel)
      case None =>