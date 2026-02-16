package com.kalay.themoviedb.domain.repository.local

import com.kalay.themoviedb.domain.model.local.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insertFavorite(favorite: Favorite)
    suspend fun deleteFavorite(id: Int)
    fun getAllFavorites(): Flow<List<Favorite>>
    suspend fun isMovieSaved(id: Int): Boolean
}
