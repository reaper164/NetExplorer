package com.reaper.data.repository

import com.reaper.domain.Resource
import com.reaper.domain.model.RouterInfo
import com.reaper.domain.repository.RouterScanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RouterScanRepositoryImpl : RouterScanRepository {

    override fun scanRouters(): Flow<Resource<List<RouterInfo>>> = flow {
        emit(Resource.Loading())



    }
}