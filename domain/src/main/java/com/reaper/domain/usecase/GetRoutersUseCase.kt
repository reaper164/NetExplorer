package com.reaper.domain.usecase

import com.reaper.domain.Resource
import com.reaper.domain.model.RouterInfo
import com.reaper.domain.model.RouterscanParams
import com.reaper.domain.repository.RouterScanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRoutersUseCase(private val repository: RouterScanRepository) {

    operator fun invoke(routerscanParams: RouterscanParams): Flow<Resource<List<RouterInfo>>> {
        if (routerscanParams.rangeIp.isNullOrEmpty() &&
            routerscanParams.listIp.isNullOrEmpty()
        ) {
            return flow { emit(Resource.Error(message = "Nothing to scan!", data = null)) }
        }
        return repository.scanRouters(routerscanParams)
    }

}