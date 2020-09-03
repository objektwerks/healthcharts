package objektwerks.chart

import java.awt.BorderLayout

import javax.swing.{JButton, JDialog, JFileChooser, JLabel, JPanel}

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  setTitle(Conf.glucoseMedsDialogTitle)

  val selectLabel = Conf.glucoseMedsDialogSelectButton
  val glucoseCsvSelectButton = new JButton(selectLabel)
  val medsCsvSelectButton = new JButton(selectLabel)
  val selectButton = new JButton(selectLabel)

  val panel = new JPanel(new MigLayout())
  panel.add( new JLabel(Conf.glucoseCsvLabel) )
  panel.add( glucoseCsvSelectButton )
  panel.add( new JLabel(Conf.medsCsvLabel) )
  panel.add( medsCsvSelectButton )
  panel.add( selectButton )
  add(panel, BorderLayout.CENTER)

  setModal(true)
  setLocationRelativeTo(frame)
  pack()
  setVisible(true)
}