package com.reaper.domain.model

data class RouterInfo(
    var auth: String? = "",
    var ssid: String? = "",
    var psk: String? = "",
    var wps: String? = "",
    var ip: String? = "0.0.0.0",
    var title: String? = "Can`t load...",
    var status: String? = "Failed",
    var bssid: String? = "",
    var lon: String? = "N/F",
    var lun: String? = "N/F",
    var port: String? = " "
)
