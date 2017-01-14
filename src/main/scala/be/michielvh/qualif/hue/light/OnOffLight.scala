package be.michielvh.qualif.hue.light

import be.michielvh.qualif.hue.light.Alert.Alert


case class OnOffLight(name: String,
                      uniqueId: String,
                      reachable: Boolean,
                      on: Boolean,
                      alert: Alert,
                      manufacturer: String,
                      deviceType: String,
                      modelId: String,
                      productId: Option[String],
                      softwareVersion: String,
                      softwareConfigurationId: Option[String]) extends Light