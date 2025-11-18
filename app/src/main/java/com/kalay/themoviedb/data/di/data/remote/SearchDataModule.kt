package com.kalay.themoviedb.data.di.data.remote

import com.kalay.themoviedb.data.remote.datasource.search.SearchRemoteDataSource
import com.kalay.themoviedb.data.remote.datasource.search.SearchRemoteDataSourceImpl
import com.kalay.themoviedb.data.remote.service.ApiSearchService
import com.kalay.themoviedb.data.repository.remote.SearchRepositoryImpl
import com.kalay.themoviedb.domain.repository.remote.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDataModule {
    @Provides
    @Singleton
    fun provideSearchRemoteDataSource(
        service: ApiSearchService
    ): SearchRemoteDataSource {
        return SearchRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(
        remoteDataSource: SearchRemoteDataSource
    ): SearchRepository {
        return SearchRepositoryImpl(remoteDataSource)
    }
}