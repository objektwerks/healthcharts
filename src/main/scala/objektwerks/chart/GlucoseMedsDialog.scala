package objektwerks.chart

import java.awt.BorderLayout

import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.{JButton, JDialog, JLabel, JPanel}

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  setTitle(Conf.glucoseMedsDialogTitle)

  val panel = buildPanel(Conf.glucoseMedsDialogSelectButton)
  add(panel, BorderLayout.CENTER)

  setModal(true)
  setLocationRelativeTo(frame)
  pack()
  setVisible(true)

  private def buildPanel(label: String): JPanel = {
    new JPanel(new MigLayout())
    panel.add( new JLabel(Conf.glucoseCsvLabel) )
    panel.add( buildGlucoseSelectButton(label) )
    panel.add( new JLabel(Conf.medsCsvLabel) )
    panel.add( buildMedsSelectButton(label) )
    panel.add( buildSelectButton(label) )
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