package com.kalay.themoviedb.data.repository.local

import com.kalay.themoviedb.data.local.datasource.FavoriteLocalDataSource
import com.kalay.themoviedb.data.mapper.toFavorite
import com.kalay.themoviedb.data.mapper.toFavoriteEntity
import com.kalay.themoviedb.domain.model.local.FavoriteDTO
import com.kalay.themoviedb.domain.repository.local.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteLocalDataSource: FavoriteLocalDataSource
) : FavoriteRepository {

    override suspend fun insertFavorite(favorite: FavoriteDTO) {
        favoriteLocalDataSource.insertFavorite(favorite.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(id: Int) {
        favoriteLocalDataSource.deleteFavorite(id)
    }

    override fun getAllFavorites(): Flow<List<FavoriteDTO>> = 
        favoriteLocalDataSource.getAllFavorites().map { entities ->
            entities.map { it.toFavorite() }
        }

    override suspend fun isMovieSaved(id: Int): Boolean = favoriteLocalDataSource.isFavorite(id)
}
