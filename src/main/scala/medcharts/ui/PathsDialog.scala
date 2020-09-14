package medcharts.ui

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{BorderLayout, Dimension}

import javax.swing._

import medcharts.Conf

import net.miginfocom.swing.MigLayout

class PathsDialog(frame: Frame, labelFirstPath: String, labelSecondPath: String) extends JDialog {
  private val firstPathTextField = buildPathTextField
  private val secondPathTextField = buildPathTextField
  private val selectButton = buildSelectButton(Conf.labelSelect)
  private val fileChooserTitle = Conf.titleFileChooser
  private val fileExtensionFilterDesc = Conf.fileExtensionFilterDesc
  private val fileExtensions = Conf.fileFilterExtensions
  private var wasNotCancelled = true

  def view: (Boolean, String, String) = {
    setTitle(Conf.titlePathsDialog)
    add(buildDialogPanel(labelFirstPath, labelSecondPath, Conf.labelCancel, Conf.labelEllipsis), BorderLayout.CENTER)
    setModal(true)
    pack()
    setLocationRelativeTo(frame)
    setVisible(true)
    (wasNotCancelled, firstPathTextField.getText, secondPathTextField.getText)
  }

  private def buildDialogPanel(labelFirstPath: String,
                               labelSecondPath: String,
                               labelCancel: String,
                               labelEllipsis: String): JPanel = {
    val panel = new JPanel( new MigLayout() )
    panel.add( new JLabel(labelFirstPath), "align label" )
    panel.add( firstPathTextField, "grow" )
    panel.add( buildFirstPathSelectButton(labelEllipsis), "wrap" )
    panel.add( new JLabel(labelSecondPath), "align label" )
    panel.add( secondPathTextField, "grow" )
    panel.add( buildSecondPathSelectButton(labelEllipsis), "wrap" )
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

  private def buildFirstPathSelectButton(ellipsisLabel: String): JButton = {
    val button = new JButton(ellipsisLabel)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        firstPathTextField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    })
    button
  }

  private def buildSecondPathSelectButton(ellipsisLabel: String): JButton = {
    val button = new JButton(ellipsisLabel)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        secondPathTextField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    })
    button
  }

  private def buildCancelButton(canceLabel: String): JButton = {
    val button = new JButton(canceLabel)
    button.addActionListener( new ActionListener() {
      override def actionPerformed(event: ActionEvent): Unit = {
        wasNotCancelled = false
        setVisible(false)
      }
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
    if (firstPathTextField.getText.nonEmpty && secondPathTextField.getText.nonEmpty) selectButton.setEnabled(true)
}