package medcharts.ui

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{BorderLayout, Dimension}

import javax.swing._

import medcharts.Conf

import net.miginfocom.swing.MigLayout

class PathDialog(frame: Frame, labelPath: String) extends JDialog {
  private val pathTextField = buildPathTextField
  private val selectButton = buildSelectButton(Conf.labelSelect)
  private val fileChooserTitle = Conf.titleFileChooser
  private val fileExtensionFilterDesc = Conf.fileExtensionFilterDesc
  private val fileExtensions = Conf.fileFilterExtensions

  def view: String = {
    setTitle(Conf.titlePathsDialog)
    add(buildDialogPanel(labelPath, Conf.labelCancel, Conf.labelEllipsis), BorderLayout.CENTER)
    setModal(true)
    pack()
    setLocationRelativeTo(frame)
    setVisible(true)
    pathTextField.getText
  }

  private def buildDialogPanel(labelPath: String,
                               labelCancel: String,
                               labelEllipsis: String): JPanel = {
    val panel = new JPanel( new MigLayout() )
    panel.add( new JLabel(labelPath), "align label" )
    panel.add( pathTextField, "grow" )
    panel.add( buildPathSelectButton(labelEllipsis), "wrap" )
    panel.add( buildCancelButton(labelCancel), "span, split 2, align right" )
    panel.add( selectButton )
    panel
  }

  private def buildPathTextField: JTextField = {
    val textField = new JTextField()
    textField.setEditable(false)
    textField.setPreferredSize(new Dimension(400, 30))
    textField
  }

  private def buildPathSelectButton(ellipsisLabel: String): JButton = {
    val button = new JButton(ellipsisLabel)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        pathTextField.setText( selectFile.getOrElse("") )
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
    if (pathTextField.getText.nonEmpty) selectButton.setEnabled(true)
}