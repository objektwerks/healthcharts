package medcharts.chart

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener}

import javax.swing.{JButton, JDialog, JFileChooser, JLabel, JPanel, JTextField}
import javax.swing.filechooser.FileSystemView
import javax.swing.filechooser.FileNameExtensionFilter

import net.miginfocom.swing.MigLayout
import java.awt.Dimension

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  private val glucoseCsvTextField = buildTextField
  private val medsCsvTextField = buildTextField
  private val selectButton = buildSelectButton(Conf.selectLabel)

  def view(): (String, String) = {
    setTitle(Conf.glucoseMedsDialogTitle)
    add(buildSelectPanel(Conf.cancelLabel), BorderLayout.CENTER)
    setModal(true)
    pack()
    setLocationRelativeTo(frame)
    setVisible(true)
    (glucoseCsvTextField.getText, medsCsvTextField.getText)
  }

  private def buildSelectPanel(cancelLabel: String): JPanel = {
    val panel = new JPanel( new MigLayout() )
    panel.add( new JLabel(Conf.glucoseCsvLabel), "align label" )
    panel.add( glucoseCsvTextField, "grow" )
    panel.add( buildGlucoseSelectButton("..."), "wrap" )
    panel.add( new JLabel(Conf.medsCsvLabel), "align label" )
    panel.add( medsCsvTextField, "grow" )
    panel.add( buildMedsSelectButton("..."), "wrap" )
    panel.add( buildCancelButton(cancelLabel), "span, split 2, align right" )
    panel.add( selectButton )
    panel
  }

  private def buildTextField: JTextField = {
    val textField = new JTextField()
    textField.setEditable(false)
    textField.setPreferredSize(new Dimension(400, 30))
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
    if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
      Some(fileChooser.getSelectedFile.getAbsolutePath)
    else None
  }

  private def validateCsvTextFields(): Unit =
    if (glucoseCsvTextField.getText.nonEmpty && medsCsvTextField.getText.nonEmpty) selectButton.setEnabled(true)
}