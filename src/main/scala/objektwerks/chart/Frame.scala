package objektwerks.chart

import javax.swing.{JFrame, WindowConstants}

class Frame() extends JFrame {  
  setJMenuBar( new MenuBar() )
  setTitle("MedCharts")
  setSize(900, 600)
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  setLocationRelativeTo(null)
}