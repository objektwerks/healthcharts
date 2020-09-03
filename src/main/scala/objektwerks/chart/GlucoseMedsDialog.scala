package objektwerks.chart

import javax.swing.JDialog

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  setTitle(Conf.glucoseMedsDialogTitle)
  setModal(true)
  setLocationRelativeTo(frame)
}