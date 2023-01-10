package com.reaper.netexplorer.di

import android.app.DownloadManager
import com.reaper.data.repository.RemoteRepositoryImpl
import com.reaper.domain.repository.RemoteRepository
import com.reaper.domain.repository.RouterScanRepository
import com.reaper.domain.usecase.DownloadChrootUseCase
import com.reaper.domain.usecase.GetRoutersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetRoutersUseCase(repository: RouterScanRepository) : GetRoutersUseCase =
         GetRoutersUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideDownloadChrootUseCase(repository: RemoteRepository) : DownloadChrootUseCase =
        DownloadChrootUseCase(repository = repository)

}