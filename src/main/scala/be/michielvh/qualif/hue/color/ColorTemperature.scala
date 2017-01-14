package be.michielvh.qualif.hue.color

import scala.math.round

object ColorTemperature {
  def mirekToKelvin(mirek: Int): Int = round(1f/mirek)
  def kelvinToMirek(kelvin: Int): Int = round(1f/kelvin)

  def fromMirek(mirek: Int): ColorTemperature = new ColorTemperature(mirekToKelvin(mirek))
  def fromKelvin(kelvin: Int): ColorTemperature = new ColorTemperature(kelvin)
}

case class ColorTemperature(kelvin: Int)