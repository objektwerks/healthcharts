package medcharts.ui

import javax.swing.JFileChooser
import javax.swing.filechooser.{FileNameExtensionFilter, FileSystemView}

object FileChooser {
  def chooseFile(frame: Frame, dialogTitle: String, fileExtensionFilter: String): Option[String] = {
    val fileChooser = new JFileChooser(FileSystemView.getFileSystemView.getHomeDirectory)
    fileChooser.setDialogTitle(dialogTitle)
    fileChooser.setAcceptAllFileFilterUsed(false)
    val filter = new FileNameExtensionFilter(fileExtensionFilter, "csv", "txt")
    fileChooser.addChoosableFileFilter(filter)
    if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
      Some(fileChooser.getSelectedFile.getAbsolutePath)
    else None
  }
}