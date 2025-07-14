package healthchart.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem, JSeparator}

import healthchart.Context
import healthchart.action.*

final class Menu(frame: Frame) extends JMenuBar():
  val menu = JMenu(Context.titleMenu)
  menu.add( JMenuItem( VitalsAction(Context.titleVitals, frame) ) )
  menu.add( JSeparator() )
  menu.add( JMenuItem( BloodPressureAction(Context.titleBloodPressure, frame) ) )
  menu.add( JMenuItem( PulseAction(Context.titlePulse, frame) ) )
  menu.add( JMenuItem( PulseOxygenAction(Context.titlePulseOxygen, frame) ) )
  menu.add( JMenuItem( RespirationAction(Context.titleRespiration, frame) ) )
  menu.add( JMenuItem( TemperatureAction(Context.titleTemperature, frame) ) )
  menu.add( JMenuItem( WeightAction(Context.titleWeight, frame) ) )
  menu.add( JSeparator() )
  menu.add( JMenuItem( GlucoseAction(Context.titleGlucose, frame) ) )
  menu.add( JMenuItem( MedAction(Context.titleMed, frame) ) )
  menu.add( JMenuItem( GlucoseMedAction(Context.titleGlucoseMed, frame) ) )
  menu.add( JSeparator() )
  menu.add( JMenuItem( CaloriesWeightAction(Context.titleCaloriesWeight, frame) ) )
  menu.add( JSeparator() )
  menu.add( JMenuItem( ExitAction(Context.menuExit) ) )
  add(menu)