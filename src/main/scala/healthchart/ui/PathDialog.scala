package healthchart.ui

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{BorderLayout, Dimension}
import javax.swing.*

import net.miginfocom.swing.MigLayout

import healthchart.Conf

class PathDialog(frame: Frame, labelPath: String) extends JDialog:
  private val pathTextField = buildPathTextField
  private val selectButton = buildSelectButton(Conf.labelSelect)
  private val fileChooserTitle = Conf.titleFileChooser
  private val fileExtensionFilterDesc = Conf.fileExtensionFilterDesc
  private val fileExtensions = Conf.fileFilterExtensions
  private var wasNotCancelled = true

  def view: Option[String] =
    setTitle(Conf.titlePathDialog)
    add(buildDialogPanel(labelPath, Conf.labelCancel, Conf.labelEllipsis), BorderLayout.CENTER)
    setModal(true)
    pack()
    setLocationRelativeTo(frame)
    setVisible(true)
    val path = pathTextField.getText
    if wasNotCancelled && path.nonEmpty then Some(path) else None

  private def buildDialogPanel(labelPath: String,
                               labelCancel: String,
                               labelEllipsis: String): JPanel =
    val panel = JPanel( MigLayout() )
    panel.add( JLabel(labelPath), "align label" )
    panel.add( pathTextField, "grow" )
    panel.add( buildPathSelectButton(labelEllipsis), "wrap" )
    panel.add( buildCancelButton(labelCancel), "span, split 2, align right" )
    panel.add( selectButton )
    panel

  private def buildPathTextField: JTextField =
    val textField = JTextField()
    textField.setEditable(false)
    textField.setPreferredSize(Dimension(400, 30))
    textField

  private def buildPathSelectButton(ellipsisLabel: String): JButton =
    val button = JButton(ellipsisLabel)
    button.addActionListener( (_: ActionEvent) => {
        pathTextField.setText( selectFile.getOrElse("") )
        validateCsvTextFields()
      }
    )
    button

  private def buildCancelButton(canceLabel: String): JButton =
    val button = JButton(canceLabel)
    button.addActionListener( (_: ActionEvent) => {
        wasNotCancelled = false
        setVisible(false)
      }
    )
    button

  private def buildSelectButton(selectLabel: String): JButton =
    val button = JButton(selectLabel)
    button.setEnabled(false)
    button.addActionListener( (_: ActionEvent) => {
      setVisible(false)
    })
    button

  private def selectFile: Option[String] = FileChooser.chooseFile(frame, fileChooserTitle, fileExtensionFilterDesc, fileExtensions)

  private def validateCsvTextFields(): Unit = if pathTextField.getText.nonEmpty then selectButton.setEnabled(true)