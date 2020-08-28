package objektwerks.chart

import org.jfree.chart.ChartPanel

import scala.util.Success
import scala.util.Failure

import Logger._
import Transformer._

object GlucoseMedsChart {
  def apply(glucoseCsvPath: String, medsCsvPath: String): ChartPanel = {
    val glucoses = transformGlucoses(glucoseCsvPath)
    val meds = transformMeds(medsCsvPath)
    build(glucoses, meds)
  }

  private def transformGlucoses(path: String): Glucoses = {
    transform[Glucoses](path) match {
      case Success(glucoses) => glucoses
      case Failure(failure) =>
        logIOFailure(failure, path)
        Glucoses.empty
    }
  }

  private def transformMeds(path: String): Meds = {
    transform[Meds](path) match {
      case Success(meds) => meds
      case Failure(failure) =>
        logIOFailure(failure, path)
        Meds.empty
    }
  }

  private def build(glucoses: Glucoses, meds: Meds): ChartPanel = {
    println(glucoses)
    println(meds)
    new ChartPanel(null)
  }
}