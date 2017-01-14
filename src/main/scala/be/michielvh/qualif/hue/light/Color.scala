package be.michielvh.qualif.hue.light

import be.michielvh.qualif.hue.color.CIEColor
import be.michielvh.qualif.hue.color.ColorMode.ColorMode
import be.michielvh.qualif.hue.light.Effect.Effect

trait Color {
  val colorMode: ColorMode
  val effect: Effect
  val hue: Int
  val saturation: Int
  val cieColor: CIEColor
}
