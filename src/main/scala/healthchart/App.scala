package healthchart

import com.formdev.flatlaf.FlatLightLaf

import java.awt.{EventQueue, Taskbar, Toolkit}
import java.awt.Taskbar.Feature

import javax.swing.UIManager

import healthchart.ui.Frame

object App:
  def main(args: Array[String]): Unit =
    EventQueue.invokeLater(
      () => {
        UIManager.setLookAndFeel( FlatLightLaf() )
        Frame().setVisible(true)

        if Taskbar.isTaskbarSupported() then
          val taskbar = Taskbar.getTaskbar()
          if taskbar.isSupported(Feature.ICON_IMAGE) then
            val defaultToolkit = Toolkit.getDefaultToolkit()
            val appIcon = defaultToolkit.getImage(getClass().getResource("/icon.png"))
            taskbar.setIconImage(appIcon)
      }
    )