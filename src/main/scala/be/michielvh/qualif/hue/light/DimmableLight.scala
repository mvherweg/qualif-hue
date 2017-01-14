package be.michielvh.qualif.hue.light

import be.michielvh.qualif.hue.light.Alert.Alert

case class DimmableLight(name: String,
                         uniqueId: String,
                         reachable: Boolean,
                         on: Boolean,
                         brightness: Int,
                         alert: Alert,
                         deviceType: String,
                         manufacturer: String,
                         modelId: String,
                         productId: Option[String],
                         softwareVersion: String,
                         softwareConfigurationId: Option[String]) extends Light with Dimmable {

}
