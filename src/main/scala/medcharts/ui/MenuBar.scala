package medcharts.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem, JSeparator}
import medcharts.Conf
import medcharts.chart.{GlucoseMedAction, TemperatureAction, WeightAction}

class MenuBar(frame: Frame) extends JMenuBar() {
  val menu = new JMenu(Conf.titleMenuBar)
  menu.add( new JMenuItem( new TemperatureAction(Conf.titleTemperature, frame) ) )
  menu.add( new JMenuItem( new WeightAction(Conf.titleWeight, frame) ) )
  menu.add( new JSeparator() )
  menu.add( new JMenuItem( new GlucoseMedAction(Conf.titleGlucoseMed, frame) ) )
  add(menu)
}