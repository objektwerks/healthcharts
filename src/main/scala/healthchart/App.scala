package healthchart

import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.util.SystemInfo

import java.awt.EventQueue
import javax.swing.UIManager

import healthchart.ui.Frame

object App:
  def main(args: Array[String]): Unit =
    EventQueue.invokeLater(
      () => {
        UIManager.setLookAndFeel( new FlatLightLaf() )
        val frame = new Frame()
        frame.setVisible(true)
      }
    )