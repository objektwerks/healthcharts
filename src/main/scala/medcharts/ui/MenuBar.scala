package medcharts.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem, JSeparator}

import medcharts.Conf
import medcharts.chart.bloodpressure.BloodPressureAction
import medcharts.chart.glucosemed.GlucoseMedAction
import medcharts.chart.pulse.PulseAction
import medcharts.chart.pulseoxygen.PulseOxygenAction
import medcharts.chart.respiration.RespirationAction
import medcharts.chart.temperature.TemperatureAction
import medcharts.chart.vitals.VitalsAction
import medcharts.chart.weight.WeightAction

class MenuBar(frame: Frame) extends JMenuBar() {
  val menu = new JMenu(Conf.titleMenuBar)
  menu.add( new JMenuItem( new VitalsAction(Conf.titleVitals, frame) ) )
  menu.add( new JSeparator() )
  menu.add( new JMenuItem( new BloodPressureAction(Conf.titleBloodPressure, frame) ) )
  menu.add( new JMenuItem( new PulseAction(Conf.titlePulse, frame) ) )
  menu.add( new JMenuItem( new PulseOxygenAction(Conf.titlePulseOxygen, frame) ) )
  menu.add( new JMenuItem( new RespirationAction(Conf.titleRespiration, frame) ) )
  menu.add( new JMenuItem( new TemperatureAction(Conf.titleTemperature, frame) ) )
  menu.add( new JMenuItem( new WeightAction(Conf.titleWeight, frame) ) )
  menu.add( new JSeparator() )
  menu.add( new JMenuItem( new GlucoseMedAction(Conf.titleGlucoseMed, frame) ) )
  add(menu)
}