package com.reaper.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RouterEntity(
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
    var port: String? = " ",
    var success: Boolean = false,
    @PrimaryKey val id: Int? = null
)


