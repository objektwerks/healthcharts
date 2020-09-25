package medcharts.action

import java.util.concurrent.atomic.AtomicInteger

import javax.swing.AbstractAction

abstract class ChartAction(name: String) extends AbstractAction(name) {
  protected val counter = new AtomicInteger(1)
}