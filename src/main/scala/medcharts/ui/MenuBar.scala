package medcharts.ui

import javax.swing.{JMenu, JMenuBar, JMenuItem}
import medcharts.Conf

class MenuBar(frame: Frame) extends JMenuBar() {
  val menu = new JMenu(Conf.menuBarTitle)
  val menuItem = new JMenuItem( new GlucoseMedsAction(Conf.glucoseMedsTitle, frame) )
  menu.add(menuItem)
  add(menu)
}