package com.kalay.themoviedb.domain.repository.local

import com.kalay.themoviedb.domain.model.local.FavoriteDTO
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun insertFavorite(favorite: FavoriteDTO)
    suspend fun deleteFavorite(id: Int)
    fun getAllFavorites(): Flow<List<FavoriteDTO>>
    suspend fun isMovieSaved(id: Int): Boolean
}
