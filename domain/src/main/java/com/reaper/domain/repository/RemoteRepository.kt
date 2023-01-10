package com.reaper.domain.repository

import com.reaper.domain.Download
import com.reaper.domain.Resource
import com.reaper.domain.model.ChrootParams
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun downloadChroot(params: ChrootParams) : Flow<Download>
}