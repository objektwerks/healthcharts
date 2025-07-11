package healthchart.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem, JSeparator}

import healthchart.Conf
import healthchart.action.*

final class MenuBar(frame: Frame) extends JMenuBar():
  val menu = JMenu(Conf.titleMenuBar)
  menu.add( JMenuItem( VitalsAction(Conf.titleVitals, frame) ) )
  menu.add( JSeparator() )
  menu.add( JMenuItem( BloodPressureAction(Conf.titleBloodPressure, frame) ) )
  menu.add( JMenuItem( PulseAction(Conf.titlePulse, frame) ) )
  menu.add( JMenuItem( PulseOxygenAction(Conf.titlePulseOxygen, frame) ) )
  menu.add( JMenuItem( RespirationAction(Conf.titleRespiration, frame) ) )
  menu.add( JMenuItem( TemperatureAction(Conf.titleTemperature, frame) ) )
  menu.add( JMenuItem( WeightAction(Conf.titleWeight, frame) ) )
  menu.add( JSeparator() )
  menu.add( JMenuItem( GlucoseAction(Conf.titleGlucose, frame) ) )
  menu.add( JMenuItem( MedAction(Conf.titleMed, frame) ) )
  menu.add( JMenuItem( GlucoseMedAction(Conf.titleGlucoseMed, frame) ) )
  menu.add( JSeparator() )
  menu.add( JMenuItem( CaloriesWeightAction(Conf.titleCaloriesWeight, frame) ) )
  add(menu)