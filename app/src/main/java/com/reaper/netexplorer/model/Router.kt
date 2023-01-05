package com.reaper.netexplorer.model

import com.reaper.domain.model.RouterInfo

data class Router(
    var auth: String? = "",
    var ssid: String? = "",
    var psk: String? = "",
    var wps: String? = "",
    var ip: String? = "0.0.0.0",
    var title: String? = "Can`t load...",
    var status: String? = "Failed",
    var bssid: String? = "",
    var port: String? = " "
)


