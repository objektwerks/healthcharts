package objektwerks.chart

import javax.swing.AbstractAction

import java.awt.event.ActionEvent

class GlucoseMedsAction(name: String, frame: Frame) extends AbstractAction(name) {
  def actionPerformed(event: ActionEvent): Unit = {
    val dialog = new GlucoseMedsDialog(frame)
    dialog.view()
  }
}