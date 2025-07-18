package healthchart

import com.formdev.flatlaf.FlatLightLaf

import java.awt.{EventQueue, Taskbar, Toolkit}
import java.awt.Taskbar.Feature

import javax.swing.UIManager

import healthchart.Logger.logInfo
import healthchart.ui.Frame

@main def runApp(): Unit =
  logInfo("*** Healthcharts starting ...")

  EventQueue.invokeLater(
    () => {
      UIManager.setLookAndFeel( FlatLightLaf() )
      Frame().setVisible(true)

      if Taskbar.isTaskbarSupported() then
        val taskbar = Taskbar.getTaskbar()
        if taskbar.isSupported(Feature.ICON_IMAGE) then
          val appIcon = Toolkit.getDefaultToolkit.getImage(this.getClass().getResource("/icon.png"))
          taskbar.setIconImage(appIcon)
    }
  )

  logInfo("*** App started!")

  sys.addShutdownHook(
    logInfo("*** App shutdown!")
  )