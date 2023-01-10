package com.reaper.netexplorer.di

import android.app.DownloadManager
import android.content.Context
import androidx.room.Room
import com.reaper.data.local.NetDatabase
import com.reaper.data.remote.api.ApiService
import com.reaper.data.repository.RemoteRepositoryImpl
import com.reaper.data.repository.RouterScanRepositoryImpl
import com.reaper.domain.repository.RemoteRepository
import com.reaper.domain.repository.RouterScanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : NetDatabase {
        return Room.databaseBuilder(context, NetDatabase::class.java, "new_db").build()
    }

    @Provides
    @Singleton
    fun provideRouterScanRepository(db : NetDatabase) : RouterScanRepository = RouterScanRepositoryImpl(db.routerDao)

    @Provides
    @Singleton
    fun provideRemoteRepository(apiService: ApiService) : RemoteRepository =
        RemoteRepositoryImpl(apiService = apiService)

    @Provides
    @Singleton
    fun provideApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


}