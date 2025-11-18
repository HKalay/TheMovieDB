package com.kalay.themoviedb.domain.di.remote

import com.kalay.themoviedb.domain.repository.remote.DiscoverRepository
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverMoviesUseCase
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverTvUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiscoverDomainModule {

    @Provides
    @Singleton
    fun provideGetDiscoverMoviesUseCase(
        repository: DiscoverRepository
    ): GetDiscoverMoviesUseCase {
        return GetDiscoverMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetDiscoverTvUseCase(
        repository: DiscoverRepository
    ): GetDiscoverTvUseCase {
        return GetDiscoverTvUseCase(repository)
    }
}