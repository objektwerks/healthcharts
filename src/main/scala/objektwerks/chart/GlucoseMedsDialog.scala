package objektwerks.chart

import java.awt.BorderLayout

import javax.swing.{JButton, JDialog, JFileChooser, JLabel, JPanel}

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  setTitle(Conf.glucoseMedsDialogTitle)

  val panel = new JPanel(new MigLayout())
  panel.add( new JLabel(Conf.glucoseCsvLabel) )
  panel.add( new JButton(Conf.glucoseMedsDialogSelectButton) )
  panel.add( new JLabel(Conf.medsCsvLabel) )
  panel.add( new JButton(Conf.glucoseMedsDialogSelectButton) )
  panel.add( new JButton(Conf.glucoseMedsDialogSelectButton) )
  add(panel, BorderLayout.CENTER)

  setModal(true)
  setLocationRelativeTo(frame)
  pack()
  setVisible(true)
}