package com.kalay.themoviedb.data.di.data.remote

import com.kalay.themoviedb.data.remote.datasource.detail.DetailRemoteDataSource
import com.kalay.themoviedb.data.remote.datasource.detail.DetailRemoteDataSourceImpl
import com.kalay.themoviedb.data.remote.service.ApiDetailService
import com.kalay.themoviedb.data.repository.remote.DetailRepositoryImpl
import com.kalay.themoviedb.domain.repository.remote.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailDataModule {

    @Provides
    @Singleton
    fun provideDetailRemoteDataSource(
        service: ApiDetailService
    ): DetailRemoteDataSource {
        return DetailRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(
        remoteDataSource: DetailRemoteDataSource
    ): DetailRepository {
        return DetailRepositoryImpl(remoteDataSource)
    }
}