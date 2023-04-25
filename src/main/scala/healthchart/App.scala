package healthchart

import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.util.SystemInfo

import java.awt.EventQueue
import javax.swing.UIManager

import healthchart.ui.Frame

/**
  * sbt run fails to load App.
  * run | debug works fine.
  */
object App:
  def main(args: Array[String]): Unit =  
    EventQueue.invokeLater(
      () => {
        UIManager.setLookAndFeel( FlatLightLaf() )
        val frame = Frame()
        frame.setVisible(true)
      }
    )