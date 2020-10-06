package healthchart

import java.awt.EventQueue

import javax.swing.UIManager._

import healthchart.ui.Frame

object App {
  def main(args: Array[String]): Unit = {
    EventQueue.invokeLater( new Runnable() {
      override def run(): Unit = {
        setLookAndFeel(getSystemLookAndFeelClassName)
        val frame = new Frame()
        frame.setVisible(true)
      }
    })
  }
}