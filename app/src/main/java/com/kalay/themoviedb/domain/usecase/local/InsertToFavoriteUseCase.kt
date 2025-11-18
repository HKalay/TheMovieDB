package com.kalay.themoviedb.domain.usecase.local

import com.kalay.themoviedb.domain.model.local.FavoriteDTO
import com.kalay.themoviedb.domain.repository.local.FavoriteRepository
import javax.inject.Inject

class InsertToFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(favorite: FavoriteDTO) = repository.insertFavorite(favorite)
}