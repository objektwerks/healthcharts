package objektwerks.chart

import java.awt.BorderLayout

import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.{JButton, JDialog, JLabel, JPanel}

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  private val pathToGlucoseCsv = Option.empty[String]
  private val pathToMedsCsv = Option.empty[String]

  def view(): (Option[String], Option[String]) = {
    setTitle(Conf.glucoseMedsDialogTitle)
    add(buildSelectPanel(Conf.glucoseMedsSelectLabel), BorderLayout.CENTER)
    setModal(true)
    setLocationRelativeTo(frame)
    pack()
    setVisible(true)
    (pathToGlucoseCsv, pathToMedsCsv)
  }

  private def buildSelectPanel(selectButtonLabel: String): JPanel = {
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