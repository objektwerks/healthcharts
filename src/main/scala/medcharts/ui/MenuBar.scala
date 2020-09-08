package medcharts.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem}

import medcharts.Conf
import medcharts.chart.GlucoseMedsAction

class MenuBar(frame: Frame) extends JMenuBar() {
  val menu = new JMenu(Conf.titleMenuBar)
  val menuItem = new JMenuItem( new GlucoseMedsAction(Conf.titleGlucoseMeds, frame) )
  menu.add(menuItem)
  add(menu)
}