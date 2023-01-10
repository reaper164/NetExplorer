package com.reaper.domain.model

typealias StartIp = String
typealias EndIp = String
typealias Range = Pair<StartIp, EndIp>

data class RouterscanParams(
    val rangeIp : List<Range>? = null,
    val listIp : List<String>? = null
)


