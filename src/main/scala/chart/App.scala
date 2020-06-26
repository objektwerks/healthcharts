package chart

import java.awt.EventQueue

object App {
  def main(args: Array[String]): Unit = {
    println("*** App main!")
    try {
      EventQueue.invokeLater( new Runnable() {
        override def run(): Unit = {
          val frame = LineChart()
          frame.setVisible(true);
        }
      })
    } catch {
      case t: Throwable => println(s"*** App Error: ${t.getMessage}")
    }
  }
}