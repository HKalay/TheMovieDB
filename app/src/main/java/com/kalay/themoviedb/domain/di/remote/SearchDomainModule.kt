package com.kalay.themoviedb.domain.di.remote

import com.kalay.themoviedb.domain.repository.remote.SearchRepository
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchMoviesUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchTvUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchDomainModule {

    @Provides
    @Singleton
    fun provideGetSearchMoviesUseCase(
        repository: SearchRepository
    ): GetSearchMoviesUseCase {
        return GetSearchMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSearchTvUseCase(
        repository: SearchRepository
    ): GetSearchTvUseCase {
        return GetSearchTvUseCase(repository)
    }
}