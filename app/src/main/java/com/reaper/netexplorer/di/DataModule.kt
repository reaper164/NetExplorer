package com.reaper.netexplorer.di

import com.reaper.data.repository.RouterScanRepositoryImpl
import com.reaper.domain.repository.RouterScanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideRouterScanRepository() : RouterScanRepository = RouterScanRepositoryImpl()

}