package com.reaper.domain.usecase

import com.reaper.domain.Download
import com.reaper.domain.Resource
import com.reaper.domain.model.ChrootParams
import com.reaper.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DownloadChrootUseCase(private val repository: RemoteRepository) {

    operator fun invoke(chrootParams: ChrootParams) : Flow<Download> {
        return repository.downloadChroot(params = chrootParams)
    }
}