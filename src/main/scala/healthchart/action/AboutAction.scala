package healthchart.action

import java.awt.event.ActionEvent
import javax.swing.{AbstractAction, JOptionPane}

import healthchart.ui.Frame

final class AboutAction(name: String,
                        message: String,
                        frame: Frame) extends AbstractAction(name):
  def actionPerformed(event: ActionEvent): Unit = JOptionPane.showMessageDialog(frame, message)