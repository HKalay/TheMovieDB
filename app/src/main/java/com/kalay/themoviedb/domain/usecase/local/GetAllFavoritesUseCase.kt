package com.kalay.themoviedb.domain.usecase.local

import com.kalay.themoviedb.domain.model.local.FavoriteDTO
import com.kalay.themoviedb.domain.repository.local.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<FavoriteDTO>> {
        return repository.getAllFavorites()
    }
}
