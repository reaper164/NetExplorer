package com.reaper.domain.repository

import com.reaper.domain.Resource
import com.reaper.domain.model.RouterInfo
import kotlinx.coroutines.flow.Flow


interface RouterScanRepository {
    fun scanRouters() : Flow<Resource<List<RouterInfo>>>
}