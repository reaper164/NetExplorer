package com.reaper.domain.repository

import com.reaper.domain.Resource
import com.reaper.domain.model.RouterInfo
import com.reaper.domain.model.RouterscanParams
import kotlinx.coroutines.flow.Flow


interface RouterScanRepository {
    fun scanRouters(routerscanParams: RouterscanParams) : Flow<Resource<List<RouterInfo>>>
}