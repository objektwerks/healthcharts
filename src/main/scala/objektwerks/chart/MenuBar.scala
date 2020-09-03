package objektwerks.chart

import javax.swing.{JMenuBar, JMenu, JMenuItem}

class MenuBar() extends JMenuBar() {  
  val menu = new JMenu("Charts")
  val menuItem = new JMenuItem( new GlucoseMedsAction("Glucose-Meds") )
  menu.add(menuItem)
  add(menu)
}