package com.reaper.netexplorer.mappers

import com.reaper.domain.model.RouterInfo
import com.reaper.netexplorer.model.Router

fun RouterInfo.toRouter() : Router {
    return Router(
        auth = auth,
        ssid = ssid,
        psk = psk,
        wps = wps,
        ip = ip,
        title = title,
        status = status,
        bssid = bssid,
        port = port
    )
}