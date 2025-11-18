package com.kalay.themoviedb.domain.di.local

import com.kalay.themoviedb.domain.repository.local.FavoriteRepository
import com.kalay.themoviedb.domain.usecase.local.DeleteFromFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.GetAllFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.InsertToFavoriteUseCase
import com.kalay.themoviedb.domain.usecase.local.IsFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDomainModule {

    @Provides
    @Singleton
    fun provideInsertToFavoriteUseCase(
        repository: FavoriteRepository
    ): InsertToFavoriteUseCase {
        return InsertToFavoriteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideIsFavoriteMovieUseCase(
        repository: FavoriteRepository
    ): IsFavoriteUseCase {
        return IsFavoriteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteFromFavoritesUseCase(
        repository: FavoriteRepository
    ): DeleteFromFavoritesUseCase {
        return DeleteFromFavoritesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllFavoritesUseCase(
        repository: FavoriteRepository
    ): GetAllFavoritesUseCase {
        return GetAllFavoritesUseCase(repository)
    }

}
