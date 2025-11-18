package com.kalay.themoviedb.data.di.data.remote

import com.kalay.themoviedb.data.remote.datasource.discover.DiscoverRemoteDataSource
import com.kalay.themoviedb.data.remote.datasource.discover.DiscoverRemoteDataSourceImpl
import com.kalay.themoviedb.data.remote.service.ApiDiscoverService
import com.kalay.themoviedb.data.repository.remote.DiscoverRepositoryImpl
import com.kalay.themoviedb.domain.repository.remote.DiscoverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverDataModule {
    @Provides
    @Singleton
    fun provideDiscoverRemoteDataSource(
        service: ApiDiscoverService
    ): DiscoverRemoteDataSource {
        return DiscoverRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideDiscoverRepository(
        remoteDataSource: DiscoverRemoteDataSource
    ): DiscoverRepository {
        return DiscoverRepositoryImpl(remoteDataSource)
    }
}