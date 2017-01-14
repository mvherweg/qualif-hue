package be.michielvh.qualif.hue.light

import be.michielvh.qualif.hue.color.ColorMode.ColorMode
import be.michielvh.qualif.hue.light.Alert.Alert

case class ColorTemperatureLight(name: String,
                                 uniqueId: String,
                                 reachable: Boolean,
                                 on: Boolean,
                                 colorMode: ColorMode,
                                 colorTemperature: Int,
                                 alert: Alert,
                                 deviceType: String,
                                 manufacturer: String,
                                 modelId: String,
                                 productId: Option[String],
                                 softwareVersion: String,
                                 softwareConfigurationId: Option[String]) extends Light with Temperature