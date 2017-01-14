package be.michielvh.qualif.hue.light

import be.michielvh.qualif.hue.color.ColorMode.ColorMode

trait Temperature {
  val colorMode: ColorMode
  val colorTemperature: Int
}
