package medcharts.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem}
import medcharts.Conf
import medcharts.chart.{GlucoseMedAction, WeightAction}

class MenuBar(frame: Frame) extends JMenuBar() {
  val menu = new JMenu(Conf.titleMenuBar)
  val glucoseMedMenuItem = new JMenuItem( new GlucoseMedAction(Conf.titleGlucoseMed, frame) )
  val weightMenuItem = new JMenuItem( new WeightAction(Conf.titleWeight, frame) )
  menu.add(glucoseMedMenuItem)
  menu.add(weightMenuItem)
  add(menu)
}