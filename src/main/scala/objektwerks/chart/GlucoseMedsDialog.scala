package objektwerks.chart

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.{JButton, JDialog, JFileChooser, JLabel, JPanel, JTextField}
import javax.swing.filechooser.FileSystemView
import javax.swing.filechooser.FileNameExtensionFilter

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  private val glucoseCsvTextField = buildTextField
  private val medsCsvTextField = buildTextField
  private val selectButton = buildSelectButton(Conf.glucoseMedsSelectLabel, this)

  def view(): (String, String) = {
    setTitle(Conf.glucoseMedsDialogTitle)
    add(buildSelectPanel(Conf.glucoseMedsSelectLabel,
                         Conf.glucoseMedsCancelLabel,
                         glucoseCsvTextField,
                         medsCsvTextField,
                         selectButton), BorderLayout.CENTER)
    setModal(true)
    setLocationRelativeTo(frame)
    pack()
    setVisible(true)
    (glucoseCsvTextField.getText, medsCsvTextField.getText)
  }

  private def buildSelectPanel(selectButtonLabel: String,
                               cancelButtonLabel: String,
                               pathToGlucoseCsvTextField: JTextField,
                               pathToMedsCsvTextField: JTextField,
                               selectButton: JButton): JPanel = {
    val panel = new JPanel( new MigLayout() )
    panel.add( new JLabel(Conf.glucoseCsvLabel), "align label" )
    panel.add( pathToGlucoseCsvTextField )
    panel.add( buildGlucoseSelectButton(selectButtonLabel, pathToGlucoseCsvTextField), "wrap" )
    panel.add( new JLabel(Conf.medsCsvLabel), "align label" )
    panel.add( pathToMedsCsvTextField )
    panel.add( buildMedsSelectButton(selectButtonLabel, pathToMedsCsvTextField), "wrap" )
    panel.add( buildCancelButton(cancelButtonLabel, this), "tag cancel, sizegroup bttn" )
    panel.add( selectButton, "tag ok, sizegroup bttn" )
    panel
  }

  private def buildTextField: JTextField = {
    val textField = new JTextField()
    textField.setEditable(false)
    textField
  }

  private def buildGlucoseSelectButton(label: String, textField: JTextField): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        textField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    })
    button
  }

  private def buildMedsSelectButton(label: String, textField: JTextField): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        textField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    })
    button
  }

  private def buildCancelButton(label: String, dialog: JDialog): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = dialog.setVisible(false)
    })
    button
  }

  private def buildSelectButton(label: String, dialog: JDialog): JButton = {
    val button = new JButton(label)
    button.setEnabled(false)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = dialog.setVisible(false)
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

  private def validateCsvTextFields(): Unit =
    if (glucoseCsvTextField.getText.nonEmpty && medsCsvTextField.getText.nonEmpty) selectButton.setEnabled(true)
}