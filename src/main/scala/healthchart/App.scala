package healthchart

import com.formdev.flatlaf.FlatLightLaf

import java.awt.EventQueue
import javax.swing.UIManager

import healthchart.ui.Frame

object App:
  def main(args: Array[String]): Unit =  
    EventQueue.invokeLater(
      () => {
        UIManager.setLookAndFeel( FlatLightLaf() )
        Frame().setVisible(true)
      }
    )