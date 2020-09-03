package objektwerks.chart

import javax.swing.{JFrame, WindowConstants}

class Frame() extends JFrame {  
  setJMenuBar( new MenuBar(this) )
  setTitle(Conf.title)
  setSize(Conf.width, Conf.height)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setLocationRelativeTo(null)
}