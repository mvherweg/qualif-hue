package be.michielvh.qualif.hue.light

import be.michielvh.qualif.hue.light.Alert.Alert

trait Light {
  val name: String
  val uniqueId: String
  val deviceType: String
  val manufacturer: String
  val modelId: String
  val softwareVersion: String
  val softwareConfigurationId: Option[String]
  val productId: Option[String]

  val reachable: Boolean
  val on: Boolean
  val alert: Alert
}
