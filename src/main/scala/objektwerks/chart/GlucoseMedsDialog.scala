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
  private val selectButton = buildSelectButton(Conf.selectLabel)

  def view(): (String, String) = {
    setTitle(Conf.glucoseMedsDialogTitle)
    add(buildSelectPanel(Conf.selectLabel, Conf.cancelLabel), BorderLayout.CENTER)
    setModal(true)
    setLocationRelativeTo(frame)
    pack()
    setVisible(true)
    (glucoseCsvTextField.getText, medsCsvTextField.getText)
  }

  private def buildSelectPanel(selectLabel: String, cancelLabel: String): JPanel = {
    val panel = new JPanel( new MigLayout() )
    panel.add( new JLabel(Conf.glucoseCsvLabel), "align label" )
    panel.add( glucoseCsvTextField )
    panel.add( buildGlucoseSelectButton(selectLabel), "wrap" )
    panel.add( new JLabel(Conf.medsCsvLabel), "align label" )
    panel.add( medsCsvTextField )
    panel.add( buildMedsSelectButton(selectLabel), "wrap" )
    panel.add( buildCancelButton(cancelLabel), "tag cancel, sizegroup bttn" )
    panel.add( selectButton, "tag ok, sizegroup bttn" )
    panel
  }

  private def buildTextField: JTextField = {
    val textField = new JTextField()
    textField.setEditable(false)
    textField
  }

  private def buildGlucoseSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        glucoseCsvTextField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    })
    button
  }

  private def buildMedsSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        medsCsvTextField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
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

  private def buildSelectButton(label: String): JButton = {
    val button = new JButton(label)
    button.setEnabled(false)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = setVisible(false)
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