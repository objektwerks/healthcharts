package objektwerks.chart

import javax.swing.{JMenuBar, JMenu, JMenuItem}

class MenuBar() extends JMenuBar() {  
  val menu = new JMenu(Conf.menuBarTitle)
  val menuItem = new JMenuItem( new GlucoseMedsAction(Conf.actionGlucoseMeds) )
  menu.add(menuItem)
  add(menu)
}