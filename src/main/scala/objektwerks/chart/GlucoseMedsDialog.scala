package objektwerks.chart

import java.awt.BorderLayout

import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.{JButton, JDialog, JLabel, JPanel}

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  setTitle(Conf.glucoseMedsDialogTitle)

  val dialogPanel = buildDialogPanel(Conf.glucoseMedsDialogSelectButton)
  add(dialogPanel, BorderLayout.CENTER)

  setModal(true)
  setLocationRelativeTo(frame)
  pack()
  setVisible(true)

  private def buildDialogPanel(selectButtonLabel: String): JPanel = {
    val panel = new JPanel(new MigLayout())
    panel.add( new JLabel(Conf.glucoseCsvLabel) )
    panel.add( buildGlucoseSelectButton(selectButtonLabel) )
    panel.add( new JLabel(Conf.medsCsvLabel) )
    panel.add( buildMedsSelectButton(selectButtonLabel) )
    panel.add( buildSelectButton(selectButtonLabel) )
    panel
  }

  private def buildGlucoseSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener(new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        println(event)
      }
    })
    button
  }

  private def buildMedsSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener(new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        println(event)
      }
    })
    button
  }

  private def buildSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener(new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        println(event)
      }
    })
    button
  }
}