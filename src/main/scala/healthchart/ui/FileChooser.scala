package healthchart.ui

import javax.swing.JFileChooser
import javax.swing.filechooser.{FileNameExtensionFilter, FileSystemView}

object FileChooser:
  def chooseFile(frame: Frame, 
                 dialogTitle: String,
                 fileExtensionFilterDesc: String,
                 fileExtensions: Array[String]): Option[String] =
    val fileChooser = JFileChooser(FileSystemView.getFileSystemView.getHomeDirectory)
    fileChooser.setDialogTitle(dialogTitle)
    fileChooser.setAcceptAllFileFilterUsed(false)
    val filter = FileNameExtensionFilter(fileExtensionFilterDesc, fileExtensions*)
    fileChooser.addChoosableFileFilter(filter)
    if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) then
      Some(fileChooser.getSelectedFile.getAbsolutePath)
    else None