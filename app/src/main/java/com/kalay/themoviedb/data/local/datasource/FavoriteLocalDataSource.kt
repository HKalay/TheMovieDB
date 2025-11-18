package com.kalay.themoviedb.data.local.datasource

import com.kalay.themoviedb.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {
    suspend fun insertFavorite(favorite: FavoriteEntity)
    suspend fun deleteFavorite(id: Int)
    fun getAllFavorites(): Flow<List<FavoriteEntity>>
    suspend fun isFavorite(movieId: Int): Boolean
}
