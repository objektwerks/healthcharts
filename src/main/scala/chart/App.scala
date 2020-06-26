package chart

import java.awt.EventQueue

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.WindowConstants

object App {
  def main(args: Array[String]): Unit = {
    println("*** App main!")
    try {
      EventQueue.invokeLater( new Runnable() {
        override def run(): Unit = {
          val frame = new JFrame()
          val chart = LineChart()
          frame.add(chart, BorderLayout.CENTER)
          frame.setTitle("JFreeChart")
          frame.setSize(640, 480)
          frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
          frame.setLocationRelativeTo(null)
          frame.setVisible(true);
        }
      })
    } catch {
      case t: Throwable => println(s"*** App Error: ${t.getMessage}")
    }
  }
}