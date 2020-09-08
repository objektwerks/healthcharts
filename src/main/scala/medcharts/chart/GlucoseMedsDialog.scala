package medcharts.chart

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{BorderLayout, Dimension}

import javax.swing._

import medcharts.Conf
import medcharts.ui.{FileChooser, Frame}

import net.miginfocom.swing.MigLayout

class GlucoseMedsDialog(frame: Frame) extends JDialog {
  private val glucoseCsvTextField = buildTextField
  private val medsCsvTextField = buildTextField
  private val selectButton = buildSelectButton(Conf.labelSelect)
  private val fileChooserTitle = Conf.titleFileChooser
  private val fileExtensionFilterDesc = Conf.fileExtensionFilterDesc
  private val fileExtensions = Conf.fileFilterExtensions

  def view(): (String, String) = {
    setTitle(Conf.titleGlucoseMedsDialog)
    add(buildSelectPanel(Conf.labelCancel, Conf.labelEllipsis), BorderLayout.CENTER)
    setModal(true)
    pack()
    setLocationRelativeTo(frame)
    setVisible(true)
    (glucoseCsvTextField.getText, medsCsvTextField.getText)
  }

  private def buildSelectPanel(cancelLabel: String, ellipsisLabel: String): JPanel = {
    val panel = new JPanel( new MigLayout() )
    panel.add( new JLabel(Conf.labelGlucoseCsv), "align label" )
    panel.add( glucoseCsvTextField, "grow" )
    panel.add( buildGlucoseSelectButton(ellipsisLabel), "wrap" )
    panel.add( new JLabel(Conf.labelMedsCsv), "align label" )
    panel.add( medsCsvTextField, "grow" )
    panel.add( buildMedsSelectButton(ellipsisLabel), "wrap" )
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

  private def buildGlucoseSelectButton(ellipsisLabel: String): JButton = {
    val button = new JButton(ellipsisLabel)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        glucoseCsvTextField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    })
    button
  }

  private def buildMedsSelectButton(ellipsisLabel: String): JButton = {
    val button = new JButton(ellipsisLabel)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        medsCsvTextField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    })
    button
  }

  private def buildCancelButton(canceLabel: String): JButton = {
    val button = new JButton(canceLabel)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = setVisible(false)
    })
    button
  }

  private def buildSelectButton(selectLabel: String): JButton = {
    val button = new JButton(selectLabel)
    button.setEnabled(false)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = setVisible(false)
    })
    button
  }

  private def selectFile: Option[String] =
    FileChooser.chooseFile(frame, fileChooserTitle, fileExtensionFilterDesc, fileExtensions)

  private def validateCsvTextFields(): Unit =
    if (glucoseCsvTextField.getText.nonEmpty && medsCsvTextField.getText.nonEmpty) selectButton.setEnabled(true)
}