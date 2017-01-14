package be.michielvh.qualif.hue.color

import be.michielvh.qualif.hue.util.Geometry.{Point, Triangle}

trait ColorGamut {
  val red: CIEColor
  val green: CIEColor
  val blue: CIEColor

  private implicit def colorToPoint(color: CIEColor): Point = Point(color.x, color.y)
  def inRange(color: CIEColor): Boolean = Triangle(red, green, blue).contains(color)
}

object GamutA extends ColorGamut {
  val red = CIEColor(0.7040f, 0.2960f)
  val green = CIEColor(0.2151f, 0.7106f)
  val blue = CIEColor(0.1380f, 0.0800f)
}

object GamutB extends ColorGamut {
  val red = CIEColor(0.6750f, 0.3220f)
  val green = CIEColor(0.4090f, 0.5180f)
  val blue = CIEColor(0.1670f, 0.0400f)
}

object GamutC extends ColorGamut {
  val red = CIEColor(0.6920f, 0.3080f)
  val green = CIEColor(0.1700f, 0.7000f)
  val blue = CIEColor(0.1530f, 0.0480f)
}
