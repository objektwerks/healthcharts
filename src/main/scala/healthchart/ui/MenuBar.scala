package healthchart.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem, JSeparator}

import healthchart.Conf
import healthchart.action.*

class MenuBar(frame: Frame) extends JMenuBar():
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
  menu.add( new JMenuItem( new GlucoseAction(Conf.titleGlucose, frame) ) )
  menu.add( new JMenuItem( new MedAction(Conf.titleMed, frame) ) )
  menu.add( new JMenuItem( new GlucoseMedAction(Conf.titleGlucoseMed, frame) ) )
  menu.add( new JSeparator() )
  menu.add( new JMenuItem( new CaloriesWeightAction(Conf.titleCaloriesWeight, frame) ) )
  add(menu)