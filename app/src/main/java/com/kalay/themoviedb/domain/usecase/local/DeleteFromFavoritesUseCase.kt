package com.kalay.themoviedb.domain.usecase.local

import com.kalay.themoviedb.domain.repository.local.FavoriteRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteFavorite(id)
    }
}
