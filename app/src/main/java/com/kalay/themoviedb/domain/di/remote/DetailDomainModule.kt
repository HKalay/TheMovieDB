package com.kalay.themoviedb.domain.di.remote

import com.kalay.themoviedb.domain.repository.remote.DetailRepository
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailMovieUseCase
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailTvShowUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailDomainModule {
    @Provides
    @Singleton
    fun provideGetDetailMovieUseCase(
        repository: DetailRepository
    ): GetDetailMovieUseCase {
        return GetDetailMovieUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetDiscoverTvUseCase(
        repository: DetailRepository
    ): GetDetailTvShowUseCase {
        return GetDetailTvShowUseCase(repository)
    }
}