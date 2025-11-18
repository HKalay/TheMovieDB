package com.kalay.themoviedb.data.di.data.local

import com.kalay.themoviedb.data.local.dao.FavoriteDao
import com.kalay.themoviedb.data.local.datasource.FavoriteLocalDataSource
import com.kalay.themoviedb.data.local.datasource.FavoriteLocalDataSourceImpl
import com.kalay.themoviedb.data.repository.local.FavoriteRepositoryImpl
import com.kalay.themoviedb.domain.repository.local.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDataModule {

    @Provides
    @Singleton
    fun provideFavoriteLocalDataSource(
        favoriteDao: FavoriteDao
    ): FavoriteLocalDataSource {
        return FavoriteLocalDataSourceImpl(favoriteDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        localDataSource: FavoriteLocalDataSource
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(localDataSource)
    }
}
