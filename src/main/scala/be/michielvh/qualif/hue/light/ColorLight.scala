package be.michielvh.qualif.hue.light

import be.michielvh.qualif.hue.color.CIEColor
import be.michielvh.qualif.hue.color.ColorMode.ColorMode
import be.michielvh.qualif.hue.light.Alert.Alert
import be.michielvh.qualif.hue.light.Effect.Effect

case class ColorLight(name: String,
                      uniqueId: String,
                      reachable: Boolean,
                      on: Boolean,
                      brightness: Int,
                      colorMode: ColorMode,
                      hue: Int,
                      saturation: Int,
                      cieColor: CIEColor,
                      alert: Alert,
                      effect: Effect,
                      deviceType: String,
                      manufacturer: String,
                      modelId: String,
                      productId: Option[String],
                      softwareVersion: String,
                      softwareConfigurationId: Option[String]) extends Light with Color with Dimmable