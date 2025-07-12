package healthchart.action

import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class ExitAction(name: String) extends AbstractAction(name):
  def actionPerformed(event: ActionEvent): Unit = System.exit(0)