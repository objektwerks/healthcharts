package objektwerks.chart

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.{JButton, JDialog, JFileChooser, JLabel, JOptionPane, JPanel, JTextField}
import javax.swing.filechooser.FileSystemView
import javax.swing.filechooser.FileNameExtensionFilter

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  private val pathToGlucoseCsvTextField = new JTextField()
  pathToGlucoseCsvTextField.setEditable(false)
  private val pathToMedsCsvTextField = new JTextField()
  pathToMedsCsvTextField.setEditable(false)

  def view(): (String, String) = {
    setTitle(Conf.glucoseMedsDialogTitle)
    add(buildSelectPanel(Conf.glucoseMedsSelectLabel,
                         Conf.glucoseMedsCancelLabel,
                         pathToGlucoseCsvTextField,
                         pathToMedsCsvTextField), BorderLayout.CENTER)
    setModal(true)
    setLocationRelativeTo(frame)
    pack()
    setVisible(true)
    (pathToGlucoseCsvTextField.getText, pathToMedsCsvTextField.getText)
  }

  private def buildSelectPanel(selectButtonLabel: String,
                               cancelButtonLabel: String,
                               pathToGlucoseCsvTextField: JTextField,
                               pathToMedsCsvTextField: JTextField): JPanel = {
    val panel = new JPanel( new MigLayout() )
    panel.add( new JLabel(Conf.glucoseCsvLabel), "align label" )
    panel.add( pathToGlucoseCsvTextField )
    panel.add( buildGlucoseSelectButton(selectButtonLabel), "wrap" )
    panel.add( new JLabel(Conf.medsCsvLabel), "align label" )
    panel.add( pathToMedsCsvTextField )
    panel.add( buildMedsSelectButton(selectButtonLabel), "wrap" )
    panel.add( buildCancelButton(cancelButtonLabel), "tag cancel, sizegroup bttn" )
    panel.add( buildSelectButton(selectButtonLabel, this), "tag ok, sizegroup bttn" )
    panel
  }

  private def buildGlucoseSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        pathToGlucoseCsvTextField.setText( selectFile.getOrElse("") )
      }
    })
    button
  }

  private def buildMedsSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        pathToMedsCsvTextField.setText( selectFile.getOrElse("") )
      }
    })
    button
  }

  private def buildCancelButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = setVisible(false)
    })
    button
  }

  private def buildSelectButton(label: String, dialog: JDialog): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        if (pathToGlucoseCsvTextField.getText.nonEmpty && pathToMedsCsvTextField.getText.nonEmpty)
          setVisible(false)
        else if (pathToGlucoseCsvTextField.getText.isEmpty || pathToMedsCsvTextField.getText.isEmpty)
          JOptionPane.showMessageDialog(dialog, Conf.glucoseMedsSelectMessage)
      }
    })
    button
  }

  private def selectFile: Option[String] = {
    val fileChooser = new JFileChooser(FileSystemView.getFileSystemView.getHomeDirectory)
    fileChooser.setDialogTitle(Conf.glucoseMedsFileChooserTitle)
    fileChooser.setAcceptAllFileFilterUsed(false)
    val filter = new FileNameExtensionFilter(Conf.glucoseMedsFileExtensionFilter, "csv", "txt")
    fileChooser.addChoosableFileFilter(filter)
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      Some(fileChooser.getSelectedFile.getAbsolutePath)
    else None
  }
}