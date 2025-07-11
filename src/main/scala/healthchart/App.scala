package healthchart

import com.formdev.flatlaf.FlatLightLaf
import com.typesafe.scalalogging.Logger

import java.awt.{EventQueue, Taskbar, Toolkit}
import java.awt.Taskbar.Feature

import javax.swing.UIManager

import healthchart.ui.Frame

@main def runApp(): Unit =
  val logger = Logger.apply(this.getClass())

  logger.info("*** Starting app ...")

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

  logger.info("*** App started!")

  sys.addShutdownHook(
    logger.info("*** App stopped!")
  )