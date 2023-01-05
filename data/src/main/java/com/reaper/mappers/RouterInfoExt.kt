package com.reaper.mappers

import com.reaper.data.local.entity.RouterEntity
import com.reaper.domain.model.RouterInfo

fun RouterEntity.toRouterInfo(): RouterInfo =
    RouterInfo(
        auth = auth,
        ssid = ssid,
        psk = psk,
        wps = wps,
        ip = ip,
        title = title,
        status = status,
        bssid = bssid,
        lon = lon,
        lun = lun,
        port = port
    )

