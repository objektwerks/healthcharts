package medcharts

import java.awt.EventQueue

import javax.swing.UIManager._

import medcharts.ui.Frame

object App {
  def main(args: Array[String]): Unit = {
    EventQueue.invokeLater( new Runnable() {
      override def run(): Unit = {
        setLookAndFeel(getCrossPlatformLookAndFeelClassName)
        val frame = new Frame()
        frame.setVisible(true)
      }
    })
  }
}